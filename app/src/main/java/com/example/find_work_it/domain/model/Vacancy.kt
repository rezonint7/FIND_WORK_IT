package com.example.find_work_it.domain.model

import com.example.find_work_it.data.remote.dto.vacancy.models.Employer
import com.example.find_work_it.data.remote.dto.vacancy.models.Salary

data class Vacancy (
    val idVacancy : String,
    val nameVacancy : String,
    val employer: Employer?,
    val salary : Salary?,
    val areaName : String?,
    val publishDate : String?,
)