package com.example.submissionfundamentaldicoding.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    fun insert(favorite: Favorite)

    @Query("SELECT * FROM favorites ORDER BY id DESC")
    fun getAllFavorite(): MutableList<Favorite>

    @Delete
    fun delete(favorite: Favorite)

}