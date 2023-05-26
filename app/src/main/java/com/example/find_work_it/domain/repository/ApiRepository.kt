package com.example.find_work_it.domain.repository

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

interface ApiRepository {
    suspend fun getVacancies(
        industryId: String = "7",
        searchField: String = "name",
        itemsCount: String = "50",
        page: String = "0",
        professionalRoleId: String = "96",
        text : String = ""
    ) : VacancyDTO
    suspend fun  getVacancyDetail(vacancyId : String) : VacancyDetailDTO
    suspend fun getSimilarVacancies(vacancyId : String) : VacancyDTO

    suspend fun getFavoriteVacancies(
        itemsCount : String = "100",
        page: String = "0"
    ) : VacancyDTO
    suspend fun putFavoriteVacancy(vacancyId : String)
    suspend fun deleteFavoriteVacancy(vacancyId : String)

    suspend fun getUserInfo(): UserDTO

    suspend fun putUserInfo(body: HashMap<String, String?>)

    suspend fun getEmployerInfo(employerId: String): EmployerDTO

    suspend fun getUserResumes(): ResumesDTO

    suspend fun getUserResumeDetail(resumeId: String): ResumeDetailDTO

    suspend fun getDictionaries(): DictionariesDTO
    suspend fun gerProfessionalRoles(): ProfessionalRolesDTO

    suspend fun getSuggestPositionsResume(text: String): SuggestPositionResumeDTO

    suspend fun getAreasResume(): AreasDTO

    suspend fun getResumeConditions(): ValidationResumeFields

    suspend fun createNewResume(newResume: ResumeDetail)
}