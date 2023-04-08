package com.example.find_work_it.data.remote.dto.vacancy.models


import com.google.gson.annotations.SerializedName

data class Counters(
    @SerializedName("responses")
    val responses: Int?
)