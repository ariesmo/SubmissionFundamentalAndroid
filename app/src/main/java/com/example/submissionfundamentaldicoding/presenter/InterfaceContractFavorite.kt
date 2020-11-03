package com.example.submissionfundamentaldicoding.presenter

import android.database.Cursor
import com.example.submissionfundamentaldicoding.db.Favorite

interface InterfaceContractFavorite {

    interface Presenter {
        fun insert(favorite: Favorite)
        fun getAllData() : Cursor
        fun delete(id: Int)
    }

    interface View {
        fun onSuccess(msg: String)
        fun onError(msg: String)
        fun showData(favorite: Cursor)
    }
}