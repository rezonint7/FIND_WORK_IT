package com.example.find_work_it.data.remote.dto.dictionary_professional_roles


import com.google.gson.annotations.SerializedName

data class Role(
    @SerializedName("accept_incomplete_resumes")
    val acceptIncompleteResumes: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("name")
    val name: String
)