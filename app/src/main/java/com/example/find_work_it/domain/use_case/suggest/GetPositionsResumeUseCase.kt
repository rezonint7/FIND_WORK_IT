package com.example.find_work_it.domain.use_case.suggest

import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.suggest.models.SuggestPositionResumeDTO
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPositionsResumeUseCase @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke(text: String): Flow<Resource<SuggestPositionResumeDTO>> = flow{
        try{
            emit(Resource.Loading())
            val suggests = repository.getSuggestPositionsResume(text)
            emit(Resource.Success(suggests))
        }catch (e: HttpException){
            emit(Resource.Error(message = ConstantsError.SUGGEST_POSITION_ERROR))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.SUGGEST_POSITION_ERROR))
        }
    }
}