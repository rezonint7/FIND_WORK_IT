package com.example.find_work_it.data.remote

import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/vacancies")
    suspend fun getVacancies() : VacancyDTO
    @GET("/vacancies/{vacancyId}")
    suspend fun getVacancyDetail(@Path("vacancyId") vacancyId : String) : VacancyDetailDTO
    @GET("/vacancies/{vacancyId}/similar_vacancies")
    suspend fun getSimilarVacancies(@Path("vacancyId") vacancyId : String) : VacancyDTO
}