package com.example.find_work_it.data.remote.dto.resumes.models


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)