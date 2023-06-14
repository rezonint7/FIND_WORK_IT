package com.example.find_work_it.presentation.screens.response_screen

import com.example.find_work_it.domain.model.Response
import com.example.find_work_it.domain.model.Vacancy

data class ResponseVacancyScreenState(
    val isLoading: Boolean = false,
    val responses: MutableList<Response> = mutableListOf(),
    val error: String = ""
)