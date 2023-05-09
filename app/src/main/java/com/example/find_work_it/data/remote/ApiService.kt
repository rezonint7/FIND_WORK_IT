package com.example.find_work_it.data.remote

import com.example.find_work_it.common.Constants
import com.example.find_work_it.data.remote.dto.employer.EmployerDTO
import com.example.find_work_it.data.remote.dto.user.UserDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDetailDTO
import com.example.find_work_it.data.remote.dto.vacancy.models.ProfessionalRole
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    //Получение вакансий
    @Headers(Constants.USER_AGENT_APP)
    @GET("/vacancies")
    suspend fun getVacancies(
        //@Header("Authorization") authHeader: String,
        @Query("industry") industryId: String,
        @Query("search_field") searchField: String,
        @Query("per_page") itemsCount: String,
        @Query("page") page: String,
        @Query("professional_role") professionalRoleId: String,
        @Query("text") text: String
    ) : VacancyDTO
    @Headers(Constants.USER_AGENT_APP)
    @GET("/vacancies/{vacancyId}")
    suspend fun getVacancyDetail(
        @Path("vacancyId") vacancyId: String
    ) : VacancyDetailDTO
    @Headers(Constants.USER_AGENT_APP)
    @GET("/vacancies/{vacancyId}/similar_vacancies")
    suspend fun getSimilarVacancies(
        @Path("vacancyId") vacancyId: String
    ) : VacancyDTO

    //Добавление и получение вакансии из списка отобранных
    @Headers(Constants.USER_AGENT_APP)
    @GET("/vacancies/favorited")
    suspend fun getFavoriteVacancies(
        @Query("per_page") itemsCount: String,
        @Query("page") page: String,
    ) : VacancyDTO

    @Headers(Constants.USER_AGENT_APP)
    @PUT("/vacancies/favorited/{vacancyId}")
    suspend fun putFavoriteVacancy(
        @Path("vacancyId") vacancyId: String
    ): Response<Unit>

    @Headers(Constants.USER_AGENT_APP)
    @DELETE("/vacancies/favorited/{vacancyId}")
    suspend fun deleteFavoriteVacancy(
        @Path("vacancyId") vacancyId: String
    ): Response<Unit>

    //Работа с профилем пользователя
    @Headers(Constants.USER_AGENT_APP)
    @GET("/me")
    suspend fun getUserInfo(): UserDTO

    @Headers(Constants.USER_AGENT_APP)
    @PUT("/me")
    suspend fun putUserInfo(@Body changeName: RequestBody): Response<Unit>

    //Работа с работадателем
    @Headers(Constants.USER_AGENT_APP)
    @GET("/employers/{employer_id}")
    suspend fun getEmployerInfo(
        @Path("employerId") employerId: String
    ): EmployerDTO
}