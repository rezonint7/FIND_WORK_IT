package com.example.find_work_it.data.remote.dto.resumes.resume_detail


import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)