package com.example.find_work_it.domain.use_case.dictionaries

import android.webkit.WebResourceRequest
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.Resource
import com.example.find_work_it.auth.AuthorizationServiceApp
import com.example.find_work_it.auth.model.Tokens
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.data.remote.dto.dictionaries.toDictionaries
import com.example.find_work_it.data.shared_prefs.SharedPrefsHelper
import com.example.find_work_it.domain.model.Dictionaries
import com.example.find_work_it.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.openid.appauth.AuthorizationService
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetDictionariesUseCase @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke(): Flow<Resource<Dictionaries>> = flow {
        try{
            val result = repository.getDictionaries().toDictionaries()
            emit(Resource.Success(result))
        }
        catch (e: Exception){
            emit(Resource.Error(message = e.localizedMessage ?: ConstantsError.ERROR_OCCURRED))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.NETWORK_ERROR))
        }
    }
}