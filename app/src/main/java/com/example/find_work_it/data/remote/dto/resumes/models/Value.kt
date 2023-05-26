package com.example.find_work_it.data.remote.dto.resumes.models


import com.google.gson.annotations.SerializedName

data class Value(
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("formatted")
    val formatted: String?,
    @SerializedName("number")
    val number: String? = null
)