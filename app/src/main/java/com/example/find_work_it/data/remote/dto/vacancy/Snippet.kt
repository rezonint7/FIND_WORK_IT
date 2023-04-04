package com.example.find_work_it.data.remote.dto.vacancy


import com.google.gson.annotations.SerializedName

data class Snippet(
    @SerializedName("requirement")
    val requirement: String,
    @SerializedName("responsibility")
    val responsibility: String
)