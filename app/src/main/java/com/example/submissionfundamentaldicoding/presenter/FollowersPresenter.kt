package com.example.submissionfundamentaldicoding.presenter

import android.util.Log
import com.example.submissionfundamentaldicoding.api.ApiService
import com.example.submissionfundamentaldicoding.model.UserFollowers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersPresenter(
    private val apiService: ApiService
) : InterfaceContractFollowers.Presenter {

    var view: InterfaceContractFollowers.View? = null
    private val TAG = FollowersPresenter::class.java.simpleName

    override fun fetchGit(username: String) {
        view?.showLoading()
        Log.d(TAG, "FollowersPresenter: Started")
        apiService.getFollowersUser(username).enqueue(object : Callback<UserFollowers>{
            override fun onResponse(call: Call<UserFollowers>, response: Response<UserFollowers>) {
                if (response.isSuccessful){
                    val model = response.body()

                    if (model != null){
                        view?.showUserGit(model)
                    } else {
                        Log.d(TAG, ""+model)
                    }
                }
                view?.hideLoading()
            }

            override fun onFailure(call: Call<UserFollowers>, t: Throwable) {
                view?.showError(t.message.toString())
            }
        })
    }
}