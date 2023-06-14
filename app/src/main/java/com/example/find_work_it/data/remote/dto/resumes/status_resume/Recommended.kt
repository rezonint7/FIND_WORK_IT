package com.example.find_work_it.data.remote.dto.resumes.status_resume


import com.google.gson.annotations.SerializedName

data class Recommended(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)