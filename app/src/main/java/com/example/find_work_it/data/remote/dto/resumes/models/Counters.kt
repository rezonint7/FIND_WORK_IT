package com.example.find_work_it.data.remote.dto.resumes.models


import com.google.gson.annotations.SerializedName

data class Counters(
    @SerializedName("total")
    val total: Int?
)