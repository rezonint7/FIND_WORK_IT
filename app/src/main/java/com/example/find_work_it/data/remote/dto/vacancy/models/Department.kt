package com.example.find_work_it.data.remote.dto.vacancy.models


import com.google.gson.annotations.SerializedName

data class Department(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)