package com.example.submissionfundamentaldicoding.presenter

import com.example.submissionfundamentaldicoding.model.UserResponseItem

interface InterfaceContractDetail {

    interface Presenter {
        fun fetchGit(username: String)
    }

    interface View {
        fun showError(msg: String)
        fun showUserGit(users: UserResponseItem)
    }

}