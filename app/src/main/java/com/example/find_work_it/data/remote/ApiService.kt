package com.example.find_work_it.data.remote

import com.example.find_work_it.data.remote.dto.dictionaries.DictionariesDTO
import com.example.find_work_it.data.remote.dto.dictionary_areas.AreasDTO
import com.example.find_work_it.data.remote.dto.dictionary_professional_roles.ProfessionalRolesDTO
import com.example.find_work_it.data.remote.dto.employer.EmployerDTO
import com.example.find_work_it.data.remote.dto.resumes.ResumeDetailDTO
import com.example.find_work_it.data.remote.dto.resumes.ResumesDTO
import com.example.find_work_it.data.remote.dto.resumes.validation_resume.ValidationResumeFields
import com.example.find_work_it.data.remote.dto.suggest.models.SuggestPositionResumeDTO
import com.example.find_work_it.data.remote.dto.user.UserDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDetailDTO
import com.example.find_work_it.domain.model.ResumeDetail
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    //Получение вакансий
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
    
    @GET("/vacancies/{vacancyId}")
    suspend fun getVacancyDetail(
        @Path("vacancyId") vacancyId: String
    ) : VacancyDetailDTO
    
    @GET("/vacancies/{vacancyId}/similar_vacancies")
    suspend fun getSimilarVacancies(
        @Path("vacancyId") vacancyId: String
    ) : VacancyDTO

    //Добавление и получение вакансии из списка отобранных
    @GET("/vacancies/favorited")
    suspend fun getFavoriteVacancies(
        @Query("per_page") itemsCount: String,
        @Query("page") page: String,
    ) : VacancyDTO

    @PUT("/vacancies/favorited/{vacancyId}")
    suspend fun putFavoriteVacancy(
        @Path("vacancyId") vacancyId: String
    ): Response<Unit>

    
    @DELETE("/vacancies/favorited/{vacancyId}")
    suspend fun deleteFavoriteVacancy(
        @Path("vacancyId") vacancyId: String
    ): Response<Unit>

    //Работа с профилем пользователя
    
    @GET("/me")
    suspend fun getUserInfo(): UserDTO

    @FormUrlEncoded
    @POST("/me")
    suspend fun putUserInfo(@FieldMap body: HashMap<String, String?>): Response<ResponseBody>

    //Работа с работадателем
    @GET("/employers/{employer_id}")
    suspend fun getEmployerInfo(
        @Path("employer_id") employerId: String
    ): EmployerDTO

    //Работа с резюме
    @GET("/resumes/mine")
    suspend fun getUserResumes(): ResumesDTO

    
    @GET("/resumes/{resume_id}")
    suspend fun getUserResumeDetail(@Path("resume_id") resumeId: String): ResumeDetailDTO

    @POST("/resumes")
    suspend fun createNewResume(@Body newResume: ResumeDetail): Call<ResumeDetail>

    @PUT("/resumes/{resume_id}")
    suspend fun editResume(
        @Path("resume_id") resumeId: String,
        @Body newResume: ResumeDetail
    ): Response<Unit>

    //Справочники
    @GET("/dictionaries")
    suspend fun getDictionaries(): DictionariesDTO

    @GET("/areas/113")
    suspend fun getAreasResume(): AreasDTO

    //Требования к полям ввода в резюме
    @GET("/resume_conditions")
    suspend fun getResumeConditions(): ValidationResumeFields

    @GET("/professional_roles")
    suspend fun gerProfessionalRoles(): ProfessionalRolesDTO

    //Подсказки
    @GET("/suggests/positions")
    suspend fun getSuggestPositionsResume(@Query("text") text: String) : SuggestPositionResumeDTO
}