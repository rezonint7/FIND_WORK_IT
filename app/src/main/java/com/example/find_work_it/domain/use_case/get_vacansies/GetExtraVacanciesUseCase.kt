package com.example.find_work_it.domain.use_case.get_vacansies

import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.ConstantsError
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

class GetExtraVacanciesUseCase @Inject constructor(private val repository: ApiRepository){
    operator fun invoke(page: String, text: String = "") : Flow<Resource<List<Vacancy>>> = flow{
        try{
            emit(Resource.Loading())
            val vacanciesDTO = repository.getVacancies(page = page, text = text)
            val pagesMap = vacanciesDTO.pagesVacancy()
            val vacancies = vacanciesDTO.items!!.map { it!!.toVacancy(pagesMap.getValue("pages"), pagesMap.getValue("page")) }
            emit(Resource.Success(vacancies))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: ConstantsError.ERROR_OCCURRED))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.NETWORK_ERROR))
        }
    }
}