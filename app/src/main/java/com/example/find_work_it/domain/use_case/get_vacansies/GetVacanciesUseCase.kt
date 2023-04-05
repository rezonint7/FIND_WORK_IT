package com.example.find_work_it.domain.use_case.get_vacansies

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.vacancy.toVacancy
import com.example.find_work_it.domain.model.Vacancy
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetVacanciesUseCase @Inject constructor(private val repository: ApiRepository){
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke() : Flow<Resource<List<Vacancy>>> = flow{
        try{
            emit(Resource.Loading())
            val vacancies = repository.getVacancies().items.map { it.toVacancy() }
            emit(Resource.Success(vacancies))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "An expected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error(message = "Что-то пошло не так... Проверьте подключение к интернету"))
        }
    }
}