package com.example.find_work_it.domain.use_case.favorites_vacancies

import android.util.Log
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

class PutFavoritesVacanciesUseCase @Inject constructor(private val repository: ApiRepository){
    operator fun invoke(vacancyId : String) : Flow<Resource<Boolean>> = flow{
        try{
            repository.putFavoriteVacancy(vacancyId)
            Log.d("PUT1", "YEEES")
            emit(Resource.Success(true))
        }catch (e: HttpException){
            Log.d("PUT1", e.localizedMessage as String)
            emit(Resource.Error(message = e.localizedMessage ?: ConstantsError.FAVORITE_ERROR_OCCURRED))
        }catch (e: IOException){
            Log.d("PUT1", e.localizedMessage as String)
            emit(Resource.Error(message = ConstantsError.FAVORITE_ERROR_NETWORK))
        }
    }
}