package com.example.find_work_it.domain.use_case.user

import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.user.toUser
import com.example.find_work_it.domain.model.User
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke() : Flow<Resource<User>> = flow{
        try{
            emit(Resource.Loading())
            val userInfo = repository.getUserInfo().toUser()
            emit(Resource.Success(userInfo))
        }catch (e: HttpException){
            emit(Resource.Error(message = Constants.USER_ACCESS_ERROR))
        }catch (e: IOException){
            emit(Resource.Error(message = Constants.NETWORK_ERROR))
        }
    }
}