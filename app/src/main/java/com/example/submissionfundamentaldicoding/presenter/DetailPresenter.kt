package com.example.submissionfundamentaldicoding.presenter

import android.content.Context
import android.util.Log
import com.example.submissionfundamentaldicoding.api.ApiService
import com.example.submissionfundamentaldicoding.model.UserResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(
    private val context: Context,
    private val apiService: ApiService
) : InterfaceContractDetail.Presenter {

    var view: InterfaceContractDetail.View? = null

    private val TAG = DetailPresenter::class.java.simpleName

    override fun fetchGit(username: String) {
        Log.d(TAG, "DetailPresenter: Started")

        apiService.getDetailUser(username).enqueue(object : Callback<UserResponseItem>{
            override fun onResponse(
                call: Call<UserResponseItem>,
                response: Response<UserResponseItem>
            ) {
                if (response.isSuccessful){
                    val model = response.body()
                    val id = model?.id
                    val sp = context.getSharedPreferences("detail", Context.MODE_PRIVATE)
                    val esp = sp.edit()
                    esp.putInt("id", id!!)
                    esp.commit()

                    if (model != null){
                        view?.showUserGit(model)
                    } else {
                        Log.d(TAG, ""+model)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponseItem>, t: Throwable) {
                view?.showError(t.message.toString())
            }

        })
    }
}