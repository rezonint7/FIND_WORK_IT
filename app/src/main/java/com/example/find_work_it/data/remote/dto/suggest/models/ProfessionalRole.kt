package com.example.find_work_it.data.remote.dto.suggest.models


import com.google.gson.annotations.SerializedName

data class ProfessionalRole(
    @SerializedName("accept_incomplete_resumes")
    val acceptIncompleteResumes: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)