package com.example.find_work_it.data.remote

import com.example.find_work_it.common.Constants
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDetailDTO
import com.example.find_work_it.data.remote.dto.vacancy.models.ProfessionalRole
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers(Constants.USER_AGENT_APP)
    @GET("/vacancies")
    suspend fun getVacancies(
        @Query("industry") industryId : String,
        @Query("search_field") searchField : String,
        @Query("per_page") itemsCount : String,
        @Query("page") page: String,
        @Query("professional_role") professionalRoleId: String,
        @Query("text") text : String
    ) : VacancyDTO
    @Headers(Constants.USER_AGENT_APP)
    @GET("/vacancies/{vacancyId}")
    suspend fun getVacancyDetail(@Path("vacancyId") vacancyId : String) : VacancyDetailDTO
    @Headers(Constants.USER_AGENT_APP)
    @GET("/vacancies/{vacancyId}/similar_vacancies")
    suspend fun getSimilarVacancies(@Path("vacancyId") vacancyId : String) : VacancyDTO
}