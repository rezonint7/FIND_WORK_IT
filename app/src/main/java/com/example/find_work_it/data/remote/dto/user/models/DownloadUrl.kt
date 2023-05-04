package com.example.find_work_it.data.remote.dto.user.models


import com.google.gson.annotations.SerializedName

data class DownloadUrl(
    @SerializedName("expires_at")
    val expiresAt: String?,
    @SerializedName("url")
    val url: String?
)