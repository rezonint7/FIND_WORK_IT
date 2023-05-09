package com.example.find_work_it.domain.use_case.employer

import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.employer.toEmployer
import com.example.find_work_it.domain.model.Employer
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEmployerInfoUseCase @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke(employerId: String): Flow<Resource<Employer>> = flow{
        try {
            emit(Resource.Loading())
            val employerDTO = repository.getEmployerInfo(employerId).toEmployer()
            emit(Resource.Success(employerDTO))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: ConstantsError.ERROR_OCCURRED))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.NETWORK_ERROR))
        }
    }
}