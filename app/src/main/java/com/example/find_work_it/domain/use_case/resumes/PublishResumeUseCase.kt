package com.example.find_work_it.domain.use_case.resumes

import android.util.Log
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.resumes.status_resume.ResumeStatusDTO
import com.example.find_work_it.data.remote.dto.resumes.toResumeDetail
import com.example.find_work_it.data.remote.dto.resumes.validation_resume.ValidationResumeFields
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PublishResumeUseCase @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke(resumeId: String) : Flow<Resource<Boolean>> = flow{
        try{
            repository.publishResume(resumeId)
            Log.d("PUBLISH", "123")
            emit(Resource.Success(true))
        }catch (e: HttpException){
            Log.d("PUBLISH", e.response()?.errorBody()?.string().toString())
            emit(Resource.Error(message = ConstantsError.NETWORK_ERROR))
        }catch (e: IOException){
            Log.d("PUBLISH", "error")
            emit(Resource.Error(message = ConstantsError.NETWORK_ERROR))
        }
    }
}