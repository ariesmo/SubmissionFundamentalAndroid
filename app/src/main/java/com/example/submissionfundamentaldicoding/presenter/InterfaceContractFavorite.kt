package com.example.submissionfundamentaldicoding.presenter

import com.example.submissionfundamentaldicoding.db.Favorite

interface InterfaceContractFavorite {

    interface Presenter {
        fun insert(favorite: Favorite)
        fun getAllData() : MutableList<Favorite>
        fun delete(favorite: Favorite)
    }

    interface View {
        fun onSuccess(msg: String)
        fun onError(msg: String)
        fun showData(favorite: MutableList<Favorite>)
    }
}