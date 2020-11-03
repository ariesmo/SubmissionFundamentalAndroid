package com.example.submissionfundamentaldicoding.presenter

import android.util.Log
import com.example.submissionfundamentaldicoding.api.ApiService
import com.example.submissionfundamentaldicoding.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(
    private val apiService: ApiService
) : InterfaceContractMain.Presenter{

    var view: InterfaceContractMain.View? = null

    private val TAG = MainPresenter::class.java.simpleName
    override fun fetchGit() {
        view?.showLoading()
        apiService.getAllUser().enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.code() == 200){
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

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                view?.showError(t.message.toString())
            }
        })
    }
}