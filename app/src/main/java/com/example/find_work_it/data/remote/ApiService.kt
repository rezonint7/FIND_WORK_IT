package com.example.find_work_it.data.remote

import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/vacancies")
    suspend fun getVacancies() : VacancyDTO
    @GET("/vacancies/{vacancyId}/similar_vacancies")
    suspend fun getSimilarVacancies(@Path("vacancyId") vacancyId : String)
}