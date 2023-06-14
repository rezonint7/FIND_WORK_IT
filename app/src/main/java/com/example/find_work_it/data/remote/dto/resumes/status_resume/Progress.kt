package com.example.find_work_it.data.remote.dto.resumes.status_resume


import com.google.gson.annotations.SerializedName

data class Progress(
    @SerializedName("mandatory")
    val mandatory: List<Mandatory>,
    @SerializedName("percentage")
    val percentage: Int,
    @SerializedName("recommended")
    val recommended: List<Recommended>
)