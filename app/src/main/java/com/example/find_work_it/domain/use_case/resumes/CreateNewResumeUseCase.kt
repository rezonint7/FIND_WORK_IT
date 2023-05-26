package com.example.find_work_it.domain.use_case.resumes

import android.util.Log
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.resumes.toResumeDetail
import com.example.find_work_it.data.remote.dto.resumes.validation_resume.ValidationResumeFields
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreateNewResumeUseCase @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke(newResume: ResumeDetail) : Flow<Resource<Boolean>> = flow{
        try{
            emit(Resource.Loading())
            repository.createNewResume(newResume)
            emit(Resource.Success(true))
        }catch (e: HttpException){
            Log.d("RESUME1",  e.response()?.errorBody()?.string().toString())
            emit(Resource.Error(message = ConstantsError.RESUME_CREATE_ERROR_OCCURRED))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.RESUME_CREATE_ERROR_NETWORK))
        }
    }
}