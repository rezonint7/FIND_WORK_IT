package com.example.find_work_it.domain.model

import com.example.find_work_it.data.remote.dto.vacancy.models.*

data class VacancyDetail(
    val idVacancy: String,
    val nameVacancy: String,
    val description: String?,
    val employer: Employer?,
    val salary: Salary?,
    val address: Address?,
    val schedule: String?,
    val experience: Experience?,
    val employment: Employment?,
    val contacts: Contacts?,
    val publishDate: String?,
)
