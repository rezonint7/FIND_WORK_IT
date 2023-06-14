package com.example.find_work_it.domain.use_case.authorization

import android.util.Log
import android.webkit.WebResourceRequest
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.Resource
import com.example.find_work_it.auth.AuthorizationServiceApp
import com.example.find_work_it.auth.model.Tokens
import com.example.find_work_it.common.ConstantsError
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
    private suspend fun getTokensOrNull(request: WebResourceRequest?): Tokens? {
        return runCatching {
            request?.url.toString().let { url ->
                when {
                    url.startsWith(Constants.REDIRECT_URI) -> {
                        suspendCoroutine<Tokens> { continuation ->
                            authorizationServiceApp.getAccessUserToken(url, authorizationService) { tokens ->
                                continuation.resume(tokens!!)
                            }
                            Log.d("AUTH", "123")
                        }.also { tokens ->
                            sharedPrefsHelper.setTokens(tokens)
                        }
                    }
                    else -> null
                }
            }
        }.getOrNull()
    }

    operator fun invoke(request: WebResourceRequest?): Flow<Resource<Tokens>> = flow {
        try{
            if(request?.url.toString().startsWith(Constants.REDIRECT_URI)){
                emit(Resource.Loading())
                val result = getTokensOrNull(request)
                Log.d("AUTH", "000000000000000000")
                emit(Resource.Success(result!!))
            }
        }
        catch (e: Exception){
            emit(Resource.Error(message = ConstantsError.ERROR_OCCURRED))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.NETWORK_ERROR))
        }
    }
}