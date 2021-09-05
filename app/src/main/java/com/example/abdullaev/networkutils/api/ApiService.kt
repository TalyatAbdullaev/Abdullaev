package com.example.abdullaev.networkutils.api

import com.example.abdullaev.networkutils.pojo.DeveloperInfo
import com.example.abdullaev.networkutils.pojo.Responce
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("random?json=true")
    fun getData(): Single<DeveloperInfo>
}