package com.example.submissionfundamentaldicoding.presenter

import android.util.Log
import com.example.submissionfundamentaldicoding.api.ApiService
import com.example.submissionfundamentaldicoding.model.UserFollowing
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingPresenter(
    private val apiService: ApiService
) : InterfaceContractFollowing.Presenter {

    var view: InterfaceContractFollowing.View? = null

    private val TAG = FollowingPresenter::class.java.simpleName

    override fun fetchGit(username: String) {
        view?.showLoading()
        apiService.getFollowingUser(username).enqueue(object : Callback<UserFollowing>{
            override fun onResponse(call: Call<UserFollowing>, response: Response<UserFollowing>) {
                if (response.isSuccessful){
                    val model = response.body()
                    Log.d(TAG, model.toString())

                    if (model != null){
                        view?.showUserGit(model)
                    } else {
                        Log.d(TAG, ""+model)
                    }
                }
                view?.hideLoading()
            }

            override fun onFailure(call: Call<UserFollowing>, t: Throwable) {
                view?.showError(t.message.toString())
            }
        })
    }
}