package com.example.find_work_it.domain.repository

import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO

interface ApiRepository {
    suspend fun getVacancies() : VacancyDTO
    suspend fun getSimilarVacancies(vacancyId : String) : VacancyDTO
}