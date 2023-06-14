package com.example.find_work_it.data.remote.dto.response_list


import com.google.gson.annotations.SerializedName

data class Vacancy(
    @SerializedName("address")
    val address: Address?,
    @SerializedName("adv_response_url")
    val advResponseUrl: Any?,
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    @SerializedName("apply_alternate_url")
    val applyAlternateUrl: String?,
    @SerializedName("archived")
    val archived: Boolean?,
    @SerializedName("area")
    val area: Area?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("department")
    val department: Any?,
    @SerializedName("employer")
    val employer: Employer?,
    @SerializedName("has_test")
    val hasTest: Boolean?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("insider_interview")
    val insiderInterview: Any?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("premium")
    val premium: Boolean?,
    @SerializedName("professional_roles")
    val professionalRoles: List<ProfessionalRole?>?,
    @SerializedName("published_at")
    val publishedAt: String?,
    @SerializedName("response_letter_required")
    val responseLetterRequired: Boolean?,
    @SerializedName("response_url")
    val responseUrl: Any?,
    @SerializedName("salary")
    val salary: Salary?,
    @SerializedName("sort_point_distance")
    val sortPointDistance: Any?,
    @SerializedName("type")
    val type: Type?,
    @SerializedName("url")
    val url: String?
)