package com.example.find_work_it.data.remote.dto.user.models


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("download_url")
    val downloadUrl: DownloadUrl?,
    @SerializedName("id")
    val id: String?
)