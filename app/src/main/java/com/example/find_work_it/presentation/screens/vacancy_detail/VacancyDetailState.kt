package com.example.find_work_it.presentation.screens.vacancy_detail

import com.example.find_work_it.data.remote.dto.vacancy.models.Employer
import com.example.find_work_it.domain.model.Vacancy
import com.example.find_work_it.domain.model.VacancyDetail

data class VacancyDetailState(
    val isLoading: Boolean = false,
    val vacancy: VacancyDetail? = null,
    val error: String = ""
)
data class SimilarVacanciesState(
    val vacancies: MutableList<Vacancy> = mutableListOf(),
    val error: String = ""
)
data class EmployerDetailState(
    val isLoading: Boolean = false,
    val employer: Employer? = null,
    val error: String = ""
)
