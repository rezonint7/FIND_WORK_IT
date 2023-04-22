package com.example.find_work_it.presentation.screens.favorite_screen

import com.example.find_work_it.domain.model.Vacancy

data class FavoritesScreenState(
    val isLoading: Boolean = false,
    val vacancies: MutableList<Vacancy> = mutableListOf(),
    val pages: Map<String, Int> = mapOf(),
    val error: String = ""
)
