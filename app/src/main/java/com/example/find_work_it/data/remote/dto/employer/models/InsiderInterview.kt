package com.example.find_work_it.data.remote.dto.employer.models


import com.google.gson.annotations.SerializedName

data class InsiderInterview(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?
)