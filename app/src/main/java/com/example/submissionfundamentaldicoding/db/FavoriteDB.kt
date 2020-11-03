package com.example.submissionfundamentaldicoding.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDB : RoomDatabase(){
    abstract fun favoriteDao(): FavoriteDao
}