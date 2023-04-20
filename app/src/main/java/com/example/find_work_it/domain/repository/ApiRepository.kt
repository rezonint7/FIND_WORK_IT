package com.example.find_work_it.domain.repository

import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDetailDTO

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
}