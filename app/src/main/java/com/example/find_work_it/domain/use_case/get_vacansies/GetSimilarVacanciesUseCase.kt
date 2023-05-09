package com.example.find_work_it.domain.use_case.get_vacansies

import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.vacancy.toVacancy
import com.example.find_work_it.domain.model.Vacancy
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSimilarVacanciesUseCase @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke(vacancyId : String): Flow<Resource<List<Vacancy>>> = flow{
        try{
            val similarVacancies = repository.getSimilarVacancies(vacancyId).items!!.map {  it!!.toVacancy() }.take(5)
            emit(Resource.Success(similarVacancies))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: ConstantsError.ERROR_OCCURRED))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.NETWORK_ERROR))
        }
    }
}