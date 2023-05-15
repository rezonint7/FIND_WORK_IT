package com.example.find_work_it.data.remote.dto.resumes.resume_detail


import com.google.gson.annotations.SerializedName

data class Recommendation(
    @SerializedName("name")
    val name: String?,
    @SerializedName("organization")
    val organization: String?,
    @SerializedName("position")
    val position: String?
)