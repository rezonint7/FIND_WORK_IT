package com.example.find_work_it.data.remote.dto.resumes.resume_detail


import com.google.gson.annotations.SerializedName

data class Site(
    @SerializedName("type")
    val type: Type?,
    @SerializedName("url")
    val url: String?
)