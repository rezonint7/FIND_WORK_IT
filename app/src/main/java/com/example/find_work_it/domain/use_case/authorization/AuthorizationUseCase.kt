package com.example.find_work_it.domain.use_case.authorization

import android.content.Context
import android.content.SharedPreferences
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.Resource
import com.example.find_work_it.common.SharedPrefsConstants
import com.example.find_work_it.common.autorization.AuthorizationServiceApp
import com.example.find_work_it.common.autorization.model.Tokens
import com.example.find_work_it.domain.model.VacancyDetail
import com.example.find_work_it.domain.repository.SharedPrefsRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthorizationUseCase @Inject constructor(
    private val authorizationService: AuthorizationService,
    private val authorizationServiceApp: AuthorizationServiceApp,
    private val sharedPrefsRepository: SharedPrefsRepository
) {
    private fun getTokensOrException(request: WebResourceRequest?): Tokens? {
        val url = request?.url.toString()
        var result: Tokens? = null
        if(request != null && url.startsWith(Constants.REDIRECT_URI)){
            authorizationServiceApp.getAccessUserToken(url, authorizationService) { tokens ->
                result = tokens
            }
        }
        return result
    }

    operator fun invoke(request: WebResourceRequest?): Flow<Resource<Tokens>> = flow {
        try{
            emit(Resource.Loading())
            val result = getTokensOrException(request)

            if(result?.access_token != null){
                sharedPrefsRepository.setTokens(result)
                emit(Resource.Success(result))
            } else emit(Resource.Error(message = "Что-то пошло не так"))
        }
        catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "Что-то пошло не так..."))
        }catch (e: IOException){
            emit(Resource.Error(message = "Что-то пошло не так... Проверьте подключение к интернету"))
        }



    }
}