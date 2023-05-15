package com.example.find_work_it.domain.use_case.resumes

import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.resumes.ResumeDetailDTO
import com.example.find_work_it.data.remote.dto.resumes.pagesResumes
import com.example.find_work_it.data.remote.dto.resumes.toResume
import com.example.find_work_it.data.remote.dto.resumes.toResumeDetail
import com.example.find_work_it.data.remote.dto.user.toUser
import com.example.find_work_it.domain.model.Resume
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.domain.model.User
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserResumeDetailUseCase @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke(resumeId: String) : Flow<Resource<ResumeDetail>> = flow{
        try{
            emit(Resource.Loading())
            val resumeDTO = repository.getUserResumeDetail(resumeId).toResumeDetail()
            emit(Resource.Success(resumeDTO))
        }catch (e: HttpException){
            emit(Resource.Error(message = ConstantsError.GET_RESUME_ERROR_OCCURRED))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.GET_RESUME_ERROR_NETWORK))
        }
    }
}