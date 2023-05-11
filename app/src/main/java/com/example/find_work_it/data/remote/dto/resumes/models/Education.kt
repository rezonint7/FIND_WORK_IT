package com.example.find_work_it.data.remote.dto.resumes.models


import com.google.gson.annotations.SerializedName

data class Education(
    @SerializedName("level")
    val level: Level?,
    @SerializedName("primary")
    val primary: List<Primary?>?
)