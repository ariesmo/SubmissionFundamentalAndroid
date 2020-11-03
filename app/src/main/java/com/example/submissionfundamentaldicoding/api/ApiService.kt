package com.example.submissionfundamentaldicoding.api

import com.example.submissionfundamentaldicoding.model.*
import com.example.submissionfundamentaldicoding.util.Constants.FOLLOWERS
import com.example.submissionfundamentaldicoding.util.Constants.FOLLOWING
import com.example.submissionfundamentaldicoding.util.Constants.USERDETAIL
import com.example.submissionfundamentaldicoding.util.Constants.USERS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(USERS)
    fun getAllUser(): Call<UserResponse>

    @GET(USERDETAIL)
    fun getDetailUser(
        @Path("username") username: String
    ): Call<UserResponseItem>

    @GET(FOLLOWERS)
    fun getFollowersUser(
        @Path("username") username: String
    ): Call<UserFollowers>

    @GET(FOLLOWING)
    fun getFollowingUser(
        @Path("username") username: String
    ): Call<UserFollowing>

}