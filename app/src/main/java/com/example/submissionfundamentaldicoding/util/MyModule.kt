package com.example.submissionfundamentaldicoding.util

import com.example.submissionfundamentaldicoding.presenter.*
import org.koin.dsl.module

val myModule = module {
    factory { MainPresenter(get()) }
    factory { DetailPresenter(get(), get()) }
    factory { FollowersPresenter(get()) }
    factory { FollowingPresenter(get()) }
    factory { FavoritePresenter(get()) }
}