package com.example.find_work_it.data.remote.dto.employer.models


import com.google.gson.annotations.SerializedName

data class Branding(
    @SerializedName("template_code")
    val templateCode: String?,
    @SerializedName("template_version_id")
    val templateVersionId: String?,
    @SerializedName("type")
    val type: String?
)