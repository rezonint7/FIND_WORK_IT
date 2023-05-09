package com.example.find_work_it.domain.model

import com.example.find_work_it.data.remote.dto.employer.models.Area
import com.example.find_work_it.data.remote.dto.employer.models.Industry
import com.example.find_work_it.data.remote.dto.employer.models.LogoUrls

data class Employer(
    val id: String?,
    val area: Area?,
    val industries: List<Industry?>?,
    val logoUrls: LogoUrls?,
    val name: String?,
    val siteUrl: String?,
    val trusted: Boolean?,
)
