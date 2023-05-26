package com.example.find_work_it.domain.use_case.resumes

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

class GetConditionsResumeUseCase @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke() : Flow<Resource<ValidationResumeFields>> = flow{
        try{
            val resumeDTO = repository.getResumeConditions()
            emit(Resource.Success(resumeDTO))
        }catch (e: HttpException){
            emit(Resource.Error(message = ConstantsError.ERROR_OCCURRED))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.ERROR_OCCURRED))
        }
    }
}