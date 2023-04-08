package com.example.find_work_it.data.remote.dto.vacancy.models


import com.google.gson.annotations.SerializedName

data class Employer(
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("logo_urls")
    val logoUrls: LogoUrls?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("trusted")
    val trusted: Boolean?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("vacancies_url")
    val vacanciesUrl: String?
)