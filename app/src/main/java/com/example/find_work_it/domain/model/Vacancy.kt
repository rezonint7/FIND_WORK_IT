package com.example.find_work_it.domain.model

import com.example.find_work_it.data.remote.dto.vacancy.Employer
import com.example.find_work_it.data.remote.dto.vacancy.Salary

data class Vacancy (
    val idVacancy : String,
    val nameVacancy : String,
    val employer: Employer,
    val salary : Salary?,
    val areaName : String,
    val publishDate : String,
)