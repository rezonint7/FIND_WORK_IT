package com.example.find_work_it.data.remote.dto.employer.models


import com.google.gson.annotations.SerializedName

data class Industry(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)