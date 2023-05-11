package com.example.find_work_it.domain.use_case.resumes

import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.resumes.pagesResumes
import com.example.find_work_it.data.remote.dto.resumes.toResume
import com.example.find_work_it.data.remote.dto.user.toUser
import com.example.find_work_it.domain.model.Resume
import com.example.find_work_it.domain.model.User
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserResumesUseCase @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke() : Flow<Resource<List<Resume>>> = flow{
        try{
            emit(Resource.Loading())
            val resumesDTO = repository.getUserResumes()
            val pagesDTO = resumesDTO.pagesResumes()
            val resumes = resumesDTO.items!!.map { it.toResume(pagesDTO.getValue("pages")!!, pagesDTO.getValue("page")!!) }
            emit(Resource.Success(resumes))
        }catch (e: HttpException){
            emit(Resource.Error(message = ConstantsError.USER_ACCESS_ERROR))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.NETWORK_ERROR))
        }
    }
}