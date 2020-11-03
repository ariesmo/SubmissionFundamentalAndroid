package com.example.submissionfundamentaldicoding.util

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.submissionfundamentaldicoding.api.DataRepository
import com.example.submissionfundamentaldicoding.db.FavoriteDB
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application(){

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        startKoin {
            androidContext(this@App)
            modules(module {
                single {
                    Room.databaseBuilder(applicationContext, FavoriteDB::class.java, "FavoriteDB")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
                single { DataRepository.created() }
            })
            modules(myModule)
        }
    }
}