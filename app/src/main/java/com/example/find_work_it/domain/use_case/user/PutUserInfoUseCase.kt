package com.example.find_work_it.domain.use_case.user

import android.util.Log
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
    operator fun invoke(body: Map<String, String?>): Flow<Resource<Boolean>> = flow{
        Log.d("123", body.getValue("first_name").toString())
        Log.d("123", body.getValue("last_name").toString())
        Log.d("123", body.getValue("middle_name").toString())
        try{
            repository.putUserInfo(body)
            emit(Resource.Success(true))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: ConstantsError.PUT_USER_ERROR_OCCURRED))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.PUT_USER_ERROR_NETWORK))
        }
    }
}