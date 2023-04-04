package com.example.find_work_it.data.remote.dto.vacancy


import com.google.gson.annotations.SerializedName

data class InsiderInterview(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)