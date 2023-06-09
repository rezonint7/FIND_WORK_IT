package com.example.find_work_it.data.repository

import com.example.find_work_it.data.remote.ApiService
import com.example.find_work_it.data.remote.dto.dictionaries.DictionariesDTO
import com.example.find_work_it.data.remote.dto.dictionary_areas.AreasDTO
import com.example.find_work_it.data.remote.dto.dictionary_professional_roles.ProfessionalRolesDTO
import com.example.find_work_it.data.remote.dto.employer.EmployerDTO
import com.example.find_work_it.data.remote.dto.response_list.ResponseListDTO
import com.example.find_work_it.data.remote.dto.resumes.ResumeDetailDTO
import com.example.find_work_it.data.remote.dto.resumes.ResumesDTO
import com.example.find_work_it.data.remote.dto.resumes.status_resume.ResumeStatusDTO
import com.example.find_work_it.data.remote.dto.resumes.validation_resume.ValidationResumeFields
import com.example.find_work_it.data.remote.dto.suggest.models.SuggestPositionResumeDTO
import com.example.find_work_it.data.remote.dto.user.UserDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDetailDTO
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val api : ApiService) : ApiRepository{
    override suspend fun getVacancies(
        industryId: String,
        searchField: String,
        itemsCount : String,
        page: String,
        professionalRoleId: String,
        text : String
    ): VacancyDTO {
        return api.getVacancies(
            industryId,
            searchField,
            itemsCount,
            page,
            professionalRoleId,
            text
        )
    }

    override suspend fun getSimilarVacancies(vacancyId: String): VacancyDTO {
        return api.getSimilarVacancies(vacancyId)
    }

    override suspend fun getFavoriteVacancies(itemsCount: String, page: String): VacancyDTO {
        return api.getFavoriteVacancies(itemsCount, page)
    }

    override suspend fun putFavoriteVacancy(vacancyId: String) {
        api.putFavoriteVacancy(vacancyId)
    }

    override suspend fun deleteFavoriteVacancy(vacancyId: String) {
        api.deleteFavoriteVacancy(vacancyId)
    }

    override suspend fun getUserInfo(): UserDTO {
        return api.getUserInfo()
    }

    override suspend fun putUserInfo(body: HashMap<String, String?>) {
       api.putUserInfo(body)
    }

    override suspend fun getEmployerInfo(employerId: String): EmployerDTO {
        return api.getEmployerInfo(employerId)
    }

    override suspend fun getUserResumes(): ResumesDTO {
        return api.getUserResumes()
    }

    override suspend fun editResume(resumeId: String, newResume: ResumeDetail) {
        api.editResume(resumeId, newResume)
    }

    override suspend fun getStatusResume(resumeId: String): ResumeStatusDTO {
        return api.getStatusResume(resumeId)
    }

    override suspend fun getSuitableResumes(vacancyId: String): ResumesDTO {
        return api.getSuitableResumes(vacancyId)
    }

    override suspend fun publishResume(resumeId: String) {
        api.publishResume(resumeId)
    }

    override suspend fun getResponseList(): ResponseListDTO {
        return api.getResponseList()
    }

    override suspend fun getUserResumeDetail(resumeId: String): ResumeDetailDTO {
        return api.getUserResumeDetail(resumeId)
    }

    override suspend fun getDictionaries(): DictionariesDTO {
        return api.getDictionaries()
    }

    override suspend fun gerProfessionalRoles(): ProfessionalRolesDTO {
        return api.gerProfessionalRoles()
    }

    override suspend fun getSuggestPositionsResume(text: String): SuggestPositionResumeDTO {
        return api.getSuggestPositionsResume(text)
    }

    override suspend fun responseVacancy(resumeId: String, vacancyId: String) {
        api.responseVacancy(resumeId, vacancyId)
    }

    override suspend fun getAreasResume(): AreasDTO {
        return api.getAreasResume()
    }

    override suspend fun getResumeConditions(): ValidationResumeFields {
        return api.getResumeConditions()
    }

    override suspend fun createNewResume(newResume: ResumeDetail) {
        api.createNewResume(newResume)
    }

    override suspend fun getVacancyDetail(vacancyId: String): VacancyDetailDTO{
        return api.getVacancyDetail(vacancyId)
    }
}