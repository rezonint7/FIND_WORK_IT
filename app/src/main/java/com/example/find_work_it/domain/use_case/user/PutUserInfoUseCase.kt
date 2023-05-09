package com.example.find_work_it.domain.use_case.user

import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.vacancy.pagesVacancy
import com.example.find_work_it.data.remote.dto.vacancy.toVacancy
import com.example.find_work_it.domain.model.User
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PutUserInfoUseCase @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke(user: User): Flow<Resource<Boolean>> = flow{
        try{
            val requestBody = RequestBody.create(
                MediaType.parse("application/x-www-form-urlencoded"),
                "first_name=${user.firstName}&last_name=${user.lastName}&middle_name=${user.middleName}"
            )
            repository.putUserInfo(requestBody)
            emit(Resource.Success(true))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: ConstantsError.PUT_USER_ERROR_OCCURRED))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.PUT_USER_ERROR_NETWORK))
        }
    }
}