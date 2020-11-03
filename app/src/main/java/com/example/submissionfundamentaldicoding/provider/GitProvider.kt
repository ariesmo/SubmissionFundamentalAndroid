package com.example.submissionfundamentaldicoding.provider

import android.content.*
import android.database.Cursor
import android.net.Uri
import com.example.submissionfundamentaldicoding.db.Favorite
import com.example.submissionfundamentaldicoding.db.FavoriteDB
import java.lang.IllegalArgumentException


class GitProvider : ContentProvider() {

    private lateinit var favoriteDB: FavoriteDB

    companion object {
        const val AUTHORITY = "com.example.submissionfundamentaldicoding.provider.GitProvider"
        val TABLE_NAME = Favorite::class.java.simpleName
        val URI_MENU = Uri.parse("content://$AUTHORITY/$TABLE_NAME")
        private const val GIT_DIR = 1
        private const val GIT_ITEM = 2
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, GIT_DIR)
            sUriMatcher.addURI(AUTHORITY, "$TABLE_NAME/#", GIT_ITEM)
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        if (context != null){
            val db = favoriteDB.getInstance(context!!)
            val count = db!!.favoriteDao().delete(ContentUris.parseId(uri))
            context!!.contentResolver.notifyChange(uri, null)
            return count
        }
        throw IllegalArgumentException("Failed to delete row into $uri")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        return when (sUriMatcher.match(uri)){
            GIT_DIR -> "vnd.android.cursor.dir/$AUTHORITY.favorites"
            GIT_ITEM -> "vnd.android.cursor.dir/$AUTHORITY.favorites"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        if (context != null){
            val db = favoriteDB.getInstance(context!!)
            val id = db!!.favoriteDao().insert(Favorite.fromContentValues(values!!))


            context!!.contentResolver.notifyChange(uri, null)
            return ContentUris.withAppendedId(uri, id)
        }
        throw IllegalArgumentException("Failed to insert row into $uri")
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        if(context != null){
            val id = ContentUris.parseId(uri)
            val db = favoriteDB.getInstance(context!!)
            val cursor = db!!.favoriteDao().getAllFavorite()
            cursor.setNotificationUri(context!!.contentResolver, uri)
            return cursor
        }
        throw IllegalArgumentException("Failed to query row for uri $uri")
    }

}
