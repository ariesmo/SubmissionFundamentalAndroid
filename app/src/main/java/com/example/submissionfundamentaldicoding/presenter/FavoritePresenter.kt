package com.example.submissionfundamentaldicoding.presenter

import android.database.Cursor
import android.util.Log
import com.example.submissionfundamentaldicoding.db.Favorite
import com.example.submissionfundamentaldicoding.db.FavoriteDB

class FavoritePresenter(private val db: FavoriteDB) : InterfaceContractFavorite.Presenter {

    var view: InterfaceContractFavorite.View? = null
    private val TAG = FavoritePresenter::class.java.simpleName

    override fun insert(favorite: Favorite) {
        db.favoriteDao().insert(favorite)
        val data = db.favoriteDao().getAllFavorite()
        view?.showData(data)

        Log.d(TAG, data.toString())
    }

    override fun getAllData(): Cursor {
        return db.favoriteDao().getAllFavorite()
    }

    override fun delete(id: Long) {
        db.favoriteDao().delete(id)
        val data = db.favoriteDao().getAllFavorite()
        view?.showData(data)
    }

//    override fun delete(favorite: Favorite) {
//        db.favoriteDao().delete(favorite)
//        val data = db.favoriteDao().getAllFavorite()
//        view?.showData(data)
//    }
}