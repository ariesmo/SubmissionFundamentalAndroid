package com.example.submissionfundamentaldicoding.presenter

import com.example.submissionfundamentaldicoding.model.UserFollowers

interface InterfaceContractFollowers {

    interface Presenter {
        fun fetchGit(username: String)
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showError(msg: String)
        fun showUserGit(users: UserFollowers)
    }
}