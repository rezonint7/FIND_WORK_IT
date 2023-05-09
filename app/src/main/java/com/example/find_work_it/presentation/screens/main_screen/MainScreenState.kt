package com.example.find_work_it.presentation.screens.main_screen

import com.example.find_work_it.domain.model.Vacancy

data class MainScreenState(
    val isLoading: Boolean = false,
    val vacancies: MutableList<Vacancy> = mutableListOf(),
    val pages: Map<String, Int> = mapOf(),
    val error: String = ""
)

data class MainExtraScreenState(
    val isLoading: Boolean = false,
    val vacancies: List<Vacancy> = emptyList(),
    val error: String = ""
)