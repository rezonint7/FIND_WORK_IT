package com.example.find_work_it.data.remote.dto.resumes.models


import com.example.find_work_it.data.remote.dto.resumes.models.Counters
import com.google.gson.annotations.SerializedName

data class SimilarVacancies(
    @SerializedName("counters")
    val counters: Counters?,
    @SerializedName("url")
    val url: String?
)