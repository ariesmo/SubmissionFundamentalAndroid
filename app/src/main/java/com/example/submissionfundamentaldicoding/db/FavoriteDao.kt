package com.example.submissionfundamentaldicoding.db

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    fun insert(favorite: Favorite) : Long

//    @Query("SELECT * FROM favorites ORDER BY id DESC")
//    fun getAllFavorite(): MutableList<Favorite>

//    @Delete
//    fun delete(favorite: Favorite)

//    @Query("DELETE FROM favorites WHERE id = :id ")
//    fun delete(id: Favorite)

    @Query("DELETE FROM favorites WHERE id = :id ")
    fun delete(id: Long): Int

//    @Query("SELECT * FROM favorites WHERE id = :id" )
//    fun delete(id: Favorite)

//
    @Query("SELECT * FROM favorites ORDER BY id DESC")
    fun getAllFavorite(): Cursor

//    @Query("SELECT * FROM favorites")
//    fun selectAll(): Cursor

}