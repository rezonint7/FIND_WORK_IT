package com.example.find_work_it.domain.model

import com.example.find_work_it.data.remote.dto.vacancy.models.Address
import com.example.find_work_it.data.remote.dto.vacancy.models.Contacts
import com.example.find_work_it.data.remote.dto.vacancy.models.Employer
import com.example.find_work_it.data.remote.dto.vacancy.models.Salary

data class VacancyDetail(
    val idVacancy : String,
    val nameVacancy : String,
    val employer: Employer?,
    val salary : Salary?,
    val address: Address?,
    val schedule : String?,
    val contacts : Contacts?,
    val publishDate : String?,
)
