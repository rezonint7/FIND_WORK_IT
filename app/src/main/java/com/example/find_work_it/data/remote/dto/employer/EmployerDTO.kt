package com.example.find_work_it.data.remote.dto.employer


import com.example.find_work_it.data.remote.dto.employer.models.ApplicantServices
import com.example.find_work_it.data.remote.dto.employer.models.Area
import com.example.find_work_it.data.remote.dto.employer.models.Branding
import com.example.find_work_it.data.remote.dto.employer.models.Industry
import com.example.find_work_it.data.remote.dto.employer.models.InsiderInterview
import com.example.find_work_it.data.remote.dto.employer.models.LogoUrls
import com.example.find_work_it.domain.model.Employer
import com.google.gson.annotations.SerializedName

data class EmployerDTO(
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    @SerializedName("applicant_services")
    val applicantServices: ApplicantServices?,
    @SerializedName("area")
    val area: Area?,
    @SerializedName("branded_description")
    val brandedDescription: String?,
    @SerializedName("branding")
    val branding: Branding?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("industries")
    val industries: List<Industry?>?,
    @SerializedName("insider_interviews")
    val insiderInterviews: List<InsiderInterview?>?,
    @SerializedName("logo_urls")
    val logoUrls: LogoUrls?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("open_vacancies")
    val openVacancies: Int?,
    @SerializedName("relations")
    val relations: List<Any?>?,
    @SerializedName("site_url")
    val siteUrl: String?,
    @SerializedName("trusted")
    val trusted: Boolean?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("vacancies_url")
    val vacanciesUrl: String?
)

fun EmployerDTO.toEmployer() : Employer{
    return Employer(
        id = id,
        area = area,
        industries = industries,
        logoUrls = logoUrls,
        name = name,
        siteUrl = siteUrl,
        trusted = trusted
    )
}