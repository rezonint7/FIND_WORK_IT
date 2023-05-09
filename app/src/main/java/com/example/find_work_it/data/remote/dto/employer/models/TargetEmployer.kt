package com.example.find_work_it.data.remote.dto.employer.models


import com.google.gson.annotations.SerializedName

data class TargetEmployer(
    @SerializedName("count")
    val count: Int?
)