package com.example.find_work_it.data.remote.dto.resumes.models


import com.google.gson.annotations.SerializedName

data class Experience(
    @SerializedName("area")
    val area: Area?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("company_id")
    val companyId: String?,
    @SerializedName("company_url")
    val companyUrl: String?,
    @SerializedName("employer")
    val employer: Employer?,
    @SerializedName("end")
    val end: String?,
    @SerializedName("industries")
    val industries: List<Industry>?,
    @SerializedName("position")
    val position: String?,
    @SerializedName("start")
    val start: String?
)