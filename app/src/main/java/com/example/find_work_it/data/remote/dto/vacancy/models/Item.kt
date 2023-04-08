package com.example.find_work_it.data.remote.dto.vacancy.models


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("address")
    val address: Address?,
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    @SerializedName("apply_alternate_url")
    val applyAlternateUrl: String?,
    @SerializedName("area")
    val area: Area?,
    @SerializedName("contacts")
    val contacts: Contacts?,
    @SerializedName("counters")
    val counters: Counters?,
    @SerializedName("department")
    val department: Department?,
    @SerializedName("employer")
    val employer: Employer,
    @SerializedName("has_test")
    val hasTest: Boolean?,
    @SerializedName("id")
    val id: String,
    @SerializedName("insider_interview")
    val insiderInterview: InsiderInterview?,
    @SerializedName("name")
    val name: String,
    @SerializedName("published_at")
    val publishedAt: String?,
    @SerializedName("response_letter_required")
    val responseLetterRequired: Boolean?,
    @SerializedName("salary")
    val salary: Salary?,
    @SerializedName("schedule")
    val schedule: Schedule?,
    @SerializedName("snippet")
    val snippet: Snippet?,
    @SerializedName("sort_point_distance")
    val sortPointDistance: Double?,
    @SerializedName("type")
    val type: Type?,
    @SerializedName("url")
    val url: String?
)