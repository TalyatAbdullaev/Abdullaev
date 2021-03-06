package com.example.abdullaev.networkutils.api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://developerslife.ru/"

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}