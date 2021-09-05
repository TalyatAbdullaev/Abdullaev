package com.example.abdullaev.networkutils.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "developer")
data class DeveloperInfo(
    @SerializedName("id")
    @Expose
    @PrimaryKey
    val id: Int? = null,
    @SerializedName("description")
    @Expose
    val description: String? = null,
    @SerializedName("gifURL")
    @Expose
    val gifURL: String? = null,
    @SerializedName("width")
    @Expose
    val width: String? = null,
    @SerializedName("height")
    @Expose
    val height: String? = null,
    @SerializedName("votes")
    @Expose
    val votes: String? = null,
    @SerializedName("author")
    @Expose
    val author: String? = null,
    @SerializedName("date")
    @Expose
    val date: String? = null,
    @SerializedName("gifSize")
    @Expose
    val gifSize: String? = null,
    @SerializedName("previewURL")
    @Expose
    val previewURL: String? = null,
    @SerializedName("videoURL")
    @Expose
    val videoURL: String? = null,
    @SerializedName("videoPath")
    @Expose
    val videoPath: String? = null,
    @SerializedName("videoSize")
    @Expose
    val videoSize: String? = null,
    @SerializedName("type")
    @Expose
    val type: String? = null,
    @SerializedName("commentsCount")
    @Expose
    val commentsCount: String? = null,
    @SerializedName("fileSize")
    @Expose
    val fileSize: String? = null,
    @SerializedName("canVote")
    @Expose
    val canVote: Boolean = true
)