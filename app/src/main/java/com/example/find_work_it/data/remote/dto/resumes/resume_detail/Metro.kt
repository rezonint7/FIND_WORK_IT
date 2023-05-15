package com.example.find_work_it.data.remote.dto.resumes.resume_detail


import com.google.gson.annotations.SerializedName

data class Metro(
    @SerializedName("id")
    val id: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lng")
    val lng: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("order")
    val order: Int?
)