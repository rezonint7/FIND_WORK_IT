package com.example.find_work_it.data.repository

import com.example.find_work_it.data.remote.ApiService
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDetailDTO
import com.example.find_work_it.domain.model.VacancyDetail
import com.example.find_work_it.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val api : ApiService) : ApiRepository{
    override suspend fun getVacancies(): VacancyDTO {
        return api.getVacancies()
    }

    override suspend fun getSimilarVacancies(vacancyId: String): VacancyDTO {
        return api.getSimilarVacancies(vacancyId)
    }

    override suspend fun getVacancyDetail(vacancyId: String): VacancyDetailDTO{
        return api.getVacancyDetail(vacancyId)
    }
}