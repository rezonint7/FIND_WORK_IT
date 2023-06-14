package com.example.find_work_it.data.remote.dto.resumes.models


import com.google.gson.annotations.SerializedName

data class Primary(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("name_id")
    val nameId: String? = null,
    @SerializedName("organization")
    val organization: String? = null,
    @SerializedName("organization_id")
    val organizationId: Any? = null,
    @SerializedName("result")
    val result: String? = null,
    @SerializedName("result_id")
    val resultId: Any? = null,
    @SerializedName("year")
    val year: Int? = null
)