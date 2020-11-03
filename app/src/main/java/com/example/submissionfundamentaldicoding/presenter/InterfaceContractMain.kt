package com.example.submissionfundamentaldicoding.presenter

import com.example.submissionfundamentaldicoding.model.UserResponse

interface InterfaceContractMain {
    interface Presenter {
        fun fetchGit()
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showError(msg: String)
        fun showUserGit(users: UserResponse)
    }
}