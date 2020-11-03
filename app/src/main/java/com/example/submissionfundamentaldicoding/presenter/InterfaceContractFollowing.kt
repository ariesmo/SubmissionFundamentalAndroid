package com.example.submissionfundamentaldicoding.presenter

import com.example.submissionfundamentaldicoding.model.UserFollowing

interface InterfaceContractFollowing {

    interface Presenter {
        fun fetchGit(username: String)
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showError(msg: String)
        fun showUserGit(users: UserFollowing)
    }
}