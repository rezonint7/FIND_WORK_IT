package com.example.find_work_it.domain.use_case.favorites_vacancies

import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.vacancy.pagesVacancy
import com.example.find_work_it.data.remote.dto.vacancy.toVacancy
import com.example.find_work_it.domain.model.Vacancy
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeleteFavoritesVacanciesUseCase @Inject constructor(private val repository: ApiRepository){
    operator fun invoke(vacancyId : String) : Flow<Resource<Boolean>> = flow{
        try{
            repository.deleteFavoriteVacancy(vacancyId)
            emit(Resource.Success(true))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "Произошла ошибка"))
        }catch (e: IOException){
            emit(Resource.Error(message = "Произошла ошибка. Проверьте подключение к интернету"))
        }
    }
}