package com.example.find_work_it.data.remote.dto.resumes.resume_detail


import com.google.gson.annotations.SerializedName

data class Language(
    @SerializedName("id")
    val id: String?,
    @SerializedName("level")
    val level: LevelX?,
    @SerializedName("name")
    val name: String?
)