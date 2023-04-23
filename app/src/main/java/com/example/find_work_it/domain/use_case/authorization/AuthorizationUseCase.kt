package com.example.find_work_it.domain.use_case.authorization

import android.util.Log
import android.webkit.WebResourceRequest
import androidx.compose.ui.platform.LocalContext
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.Resource
import com.example.find_work_it.common.autorization.AuthorizationServiceApp
import com.example.find_work_it.common.autorization.model.Tokens
import com.example.find_work_it.data.shared_prefs.SharedPrefsHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.openid.appauth.AuthorizationService
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthorizationUseCase @Inject constructor(
    private val authorizationService: AuthorizationService,
    private val authorizationServiceApp: AuthorizationServiceApp,
    private val sharedPrefsHelper: SharedPrefsHelper
) {
    private suspend fun getTokensOrException(request: WebResourceRequest?): Tokens? {
        val url = request?.url.toString()
        Log.d("URLTOKENS", url)
        var result: Tokens? = null
        if(request != null && url.startsWith(Constants.REDIRECT_URI)){
            suspendCoroutine<Tokens> {continuation ->
                authorizationServiceApp.getAccessUserToken(url, authorizationService) { tokens ->
                    continuation.resume(tokens!!)
                }
            }.let { tokens ->
                result = tokens
                sharedPrefsHelper.setTokens(tokens)
            }
        }
        return result
    }

    operator fun invoke(request: WebResourceRequest?): Flow<Resource<Tokens>> = flow {
        try{
            emit(Resource.Loading())
            val result = getTokensOrException(request)
            emit(Resource.Success(result!!))
        }
        catch (e: Exception){
            emit(Resource.Error(message = e.localizedMessage ?: "Что-то пошло не так..."))
        }catch (e: IOException){
            emit(Resource.Error(message = "Что-то пошло не так... Проверьте подключение к интернету"))
        }
    }
}