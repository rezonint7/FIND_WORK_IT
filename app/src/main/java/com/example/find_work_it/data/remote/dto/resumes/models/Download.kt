package com.example.find_work_it.data.remote.dto.resumes.models


import com.google.gson.annotations.SerializedName

data class Download(
    @SerializedName("pdf")
    val pdf: Pdf?,
    @SerializedName("rtf")
    val rtf: Rtf?
)
data class Rtf(
    @SerializedName("url")
    val url: String?
)
data class Pdf(
    @SerializedName("url")
    val url: String?
)