package com.example.find_work_it.data.remote.dto.user.models


import com.google.gson.annotations.SerializedName

data class ProfileVideos(
    @SerializedName("items")
    val items: List<Item?>?
)