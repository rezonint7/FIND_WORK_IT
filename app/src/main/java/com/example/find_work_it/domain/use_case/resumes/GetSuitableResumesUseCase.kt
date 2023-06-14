package com.example.find_work_it.domain.use_case.resumes

import android.util.Log
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.resumes.pagesResumes
import com.example.find_work_it.data.remote.dto.resumes.toResume
import com.example.find_work_it.data.remote.dto.resumes.toResumeDetail
import com.example.find_work_it.data.remote.dto.resumes.validation_resume.ValidationResumeFields
import com.example.find_work_it.domain.model.Resume
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSuitableResumesUseCase @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke(vacancyId: String) : Flow<Resource<List<Resume>>> = flow{
        try{
            emit(Resource.Loading())
            val resumesDTO = repository.getSuitableResumes(vacancyId)
            val pagesDTO = resumesDTO.pagesResumes()
            val resumes = resumesDTO.items!!.map { it.toResume(pagesDTO.getValue("pages")!!, pagesDTO.getValue("page")!!) }
            emit(Resource.Success(resumes))
        }catch (e: HttpException){
            Log.d("RESUME1",  e.response()?.errorBody()?.string().toString())
            emit(Resource.Error(message = ConstantsError.RESUME_CREATE_ERROR_OCCURRED))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.RESUME_CREATE_ERROR_NETWORK))
        }
    }
}