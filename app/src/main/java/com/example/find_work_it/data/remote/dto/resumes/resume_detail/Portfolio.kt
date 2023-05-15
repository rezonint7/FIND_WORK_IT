package com.example.find_work_it.data.remote.dto.resumes.resume_detail


import com.google.gson.annotations.SerializedName

data class Portfolio(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("small")
    val small: String?
)