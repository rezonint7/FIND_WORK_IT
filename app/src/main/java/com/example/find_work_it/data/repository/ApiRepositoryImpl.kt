package com.example.find_work_it.data.repository

import com.example.find_work_it.data.remote.ApiService
import com.example.find_work_it.data.remote.dto.user.UserDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDetailDTO
import com.example.find_work_it.domain.model.VacancyDetail
import com.example.find_work_it.domain.repository.ApiRepository
import okhttp3.RequestBody
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

    override suspend fun putUserInfo(changeName: RequestBody) {
       api.putUserInfo(changeName)
    }

    override suspend fun getVacancyDetail(vacancyId: String): VacancyDetailDTO{
        return api.getVacancyDetail(vacancyId)
    }
}