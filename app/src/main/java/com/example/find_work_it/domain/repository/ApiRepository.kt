package com.example.find_work_it.domain.repository

import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDetailDTO

interface ApiRepository {
    suspend fun getVacancies() : VacancyDTO
    suspend fun  getVacancyDetail(vacancyId : String) : VacancyDetailDTO
    suspend fun getSimilarVacancies(vacancyId : String) : VacancyDTO
}