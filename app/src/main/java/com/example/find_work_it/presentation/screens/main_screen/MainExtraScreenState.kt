package com.example.find_work_it.presentation.screens.main_screen

import com.example.find_work_it.domain.model.Vacancy

data class MainExtraScreenState(
    val isLoading: Boolean = false,
    val vacancies: List<Vacancy> = emptyList(),
    val error: String = ""
)
