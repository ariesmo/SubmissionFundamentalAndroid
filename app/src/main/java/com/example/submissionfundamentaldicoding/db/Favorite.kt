package com.example.submissionfundamentaldicoding.db

import android.content.ContentValues
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
class Favorite(
    @PrimaryKey(autoGenerate = true) val idn: Int? = null,
    @ColumnInfo(name = "id") var id: Int? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "avatar") var avatar: String? = null
) {
    companion object {
        fun fromContentValues(values: ContentValues): Favorite {
            val favorite = Favorite()
            if (values.containsKey("id")) favorite.id = values.getAsInteger("id")
            if (values.containsKey("name")) favorite.name = values.getAsString("name")
            if (values.containsKey("avatar")) favorite.avatar = values.getAsString("avatar")
            return favorite
        }
    }
}