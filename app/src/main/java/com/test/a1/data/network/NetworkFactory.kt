package com.test.a1.data.network

import com.test.a1.data.network.response.SplashApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkFactory {

    private const val BASE_URL = "http://84.38.181.162/api/"
    private const val SPLASH_URL = "http://94.130.75.196/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()


    private val splashRetrofit = Retrofit.Builder()
        .baseUrl(SPLASH_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val splashService = splashRetrofit.create(SplashApi::class.java)

    val apiService =  retrofit.create(NetworkApi::class.java)
}