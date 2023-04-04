package com.example.find_work_it.domain.model

import com.example.find_work_it.data.remote.dto.vacancy.Address
import com.example.find_work_it.data.remote.dto.vacancy.Contacts
import com.example.find_work_it.data.remote.dto.vacancy.Employer
import com.example.find_work_it.data.remote.dto.vacancy.Salary

data class VacancyDetail(
    val idVacancy : String,
    val nameVacancy : String,
    val employer: Employer,
    val salary : Salary?,
    val address: Address?,
    val schedule : String?,
    val contacts : Contacts?,
    val publishDate : String,
)
