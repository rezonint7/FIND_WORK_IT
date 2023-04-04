package com.example.find_work_it.data.remote.dto.vacancy


import com.google.gson.annotations.SerializedName

data class Phone(
    @SerializedName("city")
    val city: String,
    @SerializedName("comment")
    val comment: Any?,
    @SerializedName("country")
    val country: String,
    @SerializedName("number")
    val number: String
)