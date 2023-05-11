package com.example.find_work_it.data.remote.dto.resumes.models


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("access")
    val access: Access?,
    @SerializedName("age")
    val age: Int?,
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    @SerializedName("area")
    val area: Area?,
    @SerializedName("blocked")
    val blocked: Boolean?,
    @SerializedName("can_publish_or_update")
    val canPublishOrUpdate: Boolean?,
    @SerializedName("certificate")
    val certificate: List<Certificate>?,
    @SerializedName("contact")
    val contact: List<Contact>?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("download")
    val download: Download?,
    @SerializedName("education")
    val education: Education?,
    @SerializedName("experience")
    val experience: List<Experience>?,
    @SerializedName("finished")
    val finished: Boolean,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("gender")
    val gender: Gender?,
    @SerializedName("hidden_fields")
    val hiddenFields: List<HiddenField>?,
    @SerializedName("id")
    val id: String,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("middle_name")
    val middleName: String?,
    @SerializedName("new_views")
    val newViews: Int?,
    @SerializedName("next_publish_at")
    val nextPublishAt: String?,
    @SerializedName("paid_services")
    val paidServices: List<PaidService>?,
    @SerializedName("photo")
    val photo: Photo?,
    @SerializedName("platform")
    val platform: Platform?,
    @SerializedName("salary")
    val salary: Salary?,
    @SerializedName("similar_vacancies")
    val similarVacancies: SimilarVacancies?,
    @SerializedName("status")
    val status: Status,
    @SerializedName("title")
    val title: String?,
    @SerializedName("total_experience")
    val totalExperience: TotalExperience?,
    @SerializedName("total_views")
    val totalViews: Int?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("views_url")
    val viewsUrl: String?
)