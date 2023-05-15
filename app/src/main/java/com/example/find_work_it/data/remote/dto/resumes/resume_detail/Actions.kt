package com.example.find_work_it.data.remote.dto.resumes.resume_detail


import com.example.find_work_it.data.remote.dto.resumes.models.Download
import com.google.gson.annotations.SerializedName

data class Actions(
    @SerializedName("download")
    val download: Download?
)