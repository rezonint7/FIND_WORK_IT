package com.example.find_work_it.data.remote.dto.resumes.models


import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("comment")
    val comment: String?,
    @SerializedName("preferred")
    val preferred: Boolean?,
    @SerializedName("type")
    val type: Type?,
    @SerializedName("value")
    val value: Any?,
    @SerializedName("verified")
    val verified: Boolean?
)