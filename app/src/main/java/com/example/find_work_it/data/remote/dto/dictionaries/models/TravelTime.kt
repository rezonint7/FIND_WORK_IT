package com.example.find_work_it.data.remote.dto.dictionaries.models


import com.google.gson.annotations.SerializedName

data class TravelTime(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)