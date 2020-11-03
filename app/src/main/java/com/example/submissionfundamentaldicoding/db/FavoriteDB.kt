package com.example.submissionfundamentaldicoding.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Favorite::class], version = 1,  exportSchema = false)
abstract class FavoriteDB : RoomDatabase(){

    private val DATABASE_NAME = "FavoriteDB"
    private var INSTANCE: FavoriteDB? = null

    open fun getInstance(context: Context): FavoriteDB? {
        if (INSTANCE == null) {
            INSTANCE = Room
                .databaseBuilder(context, FavoriteDB::class.java, DATABASE_NAME).build()
        }
        return INSTANCE
    }

    abstract fun favoriteDao(): FavoriteDao
}