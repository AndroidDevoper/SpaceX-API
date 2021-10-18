package com.example.spacex_api.model

import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitApi {
    @GET("v4/launches")
    fun getLaunches(): Single<List<LaunchRequest>>
}