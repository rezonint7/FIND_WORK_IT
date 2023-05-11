package com.example.find_work_it.data.remote.dto.resumes.models


import com.google.gson.annotations.SerializedName

data class Certificate(
    @SerializedName("achieved_at")
    val achievedAt: String?,
    @SerializedName("owner")
    val owner: Any?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)