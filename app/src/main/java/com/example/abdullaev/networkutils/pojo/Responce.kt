package com.example.abdullaev.networkutils.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class Responce(
    @SerializedName("result")
    @Expose
    var result: List<DeveloperInfo> = arrayListOf()
)