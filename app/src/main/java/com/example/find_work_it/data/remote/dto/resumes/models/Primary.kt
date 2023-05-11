package com.example.find_work_it.data.remote.dto.resumes.models


import com.google.gson.annotations.SerializedName

data class Primary(
    @SerializedName("name")
    val name: String?,
    @SerializedName("name_id")
    val nameId: String?,
    @SerializedName("organization")
    val organization: String?,
    @SerializedName("organization_id")
    val organizationId: Any?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("result_id")
    val resultId: Any?,
    @SerializedName("year")
    val year: Int?
)