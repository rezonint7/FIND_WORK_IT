package com.example.find_work_it.data.remote

import com.example.find_work_it.common.Constants
import com.example.find_work_it.data.remote.dto.dictionaries.DictionariesDTO
import com.example.find_work_it.data.remote.dto.dictionary_areas.AreasDTO
import com.example.find_work_it.data.remote.dto.employer.EmployerDTO
import com.example.find_work_it.data.remote.dto.resumes.ResumeDetailDTO
import com.example.find_work_it.data.remote.dto.resumes.ResumesDTO
import com.example.find_work_it.data.remote.dto.suggest.models.SuggestPositionResumeDTO
import com.example.find_work_it.data.remote.dto.user.UserDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDetailDTO
import okhttp3.ResponseBody
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

    @Headers(
        Constants.USER_AGENT_APP,
    )
    @FormUrlEncoded
    @POST("/me")
    suspend fun putUserInfo(@FieldMap body: HashMap<String, String?>): Response<ResponseBody>

    //Работа с работадателем
    @Headers(Constants.USER_AGENT_APP)
    @GET("/employers/{employer_id}")
    suspend fun getEmployerInfo(
        @Path("employer_id") employerId: String
    ): EmployerDTO

    //Работа с резюме
    @Headers(Constants.USER_AGENT_APP)
    @GET("/resumes/mine")
    suspend fun getUserResumes(): ResumesDTO

    @Headers(Constants.USER_AGENT_APP)
    @GET("/resumes/{resume_id}")
    suspend fun getUserResumeDetail(@Path("resume_id") resumeId: String): ResumeDetailDTO

    @POST("/resumes")
    suspend fun createNewResume()

    @PUT("/resumes/{resume_id}")
    suspend fun editResume(
        @Path("resume_id") resumeId: String,


    )

    //Справочники
    @Headers(Constants.USER_AGENT_APP)
    @GET("/dictionaries")
    suspend fun getDictionaries(): DictionariesDTO

    @Headers(Constants.USER_AGENT_APP)
    @GET("/areas/113")
    suspend fun getAreasResume(): AreasDTO

    //Подсказки
    @Headers(Constants.USER_AGENT_APP)
    @GET("/suggests/positions")
    suspend fun getSuggestPositionsResume(@Query("text") text: String) : SuggestPositionResumeDTO
}