package com.example.find_work_it.data.remote.dto.resumes.resume_detail


import com.google.gson.annotations.SerializedName

data class Relocation(
    @SerializedName("area")
    val area: List<Area>?,
    @SerializedName("type")
    val type: TypeX?
)