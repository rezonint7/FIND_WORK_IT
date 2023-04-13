package com.example.find_work_it.presentation.screens.vacancy_detail

import com.example.find_work_it.domain.model.VacancyDetail

data class VacancyDetailState(
    val isLoading: Boolean = false,
    val vacancy: VacancyDetail? = null,
    val error: String = ""
)
