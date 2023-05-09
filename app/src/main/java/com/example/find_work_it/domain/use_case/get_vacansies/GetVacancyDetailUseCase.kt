package com.example.find_work_it.domain.use_case.get_vacansies

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.vacancy.toVacancyDetail
import com.example.find_work_it.domain.model.VacancyDetail
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetVacancyDetailUseCase @Inject constructor(private val repository: ApiRepository){
    operator fun invoke(vacancyId: String) : Flow<Resource<VacancyDetail>> = flow{
        try{
            emit(Resource.Loading())
            val vacancy = repository.getVacancyDetail(vacancyId).toVacancyDetail()
            emit(Resource.Success(vacancy))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: ConstantsError.ERROR_OCCURRED))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.NETWORK_ERROR))
        }
    }
}