package com.example.find_work_it.domain.use_case.user

import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.Resource
import com.example.find_work_it.auth.AuthorizationServiceApp
import com.example.find_work_it.auth.model.Tokens
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.data.shared_prefs.SharedPrefsHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.openid.appauth.AuthorizationService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RefreshUserTokensUseCase @Inject constructor(
    private val authorizationService: AuthorizationService,
    private val authorizationServiceApp: AuthorizationServiceApp,
    private val sharedPrefsHelper: SharedPrefsHelper
) {
    private suspend fun refreshTokensOrNull(): Tokens? {
        return runCatching {
            sharedPrefsHelper.getTokens()?.refresh_token?.let { refreshToken ->
                suspendCoroutine<Tokens> { continuation ->
                    authorizationServiceApp.refreshAccessUserToken(refreshToken, authorizationService) { tokens ->
                        continuation.resume(tokens!!)
                    }
                }
            }
        }.onSuccess { tokens ->
            sharedPrefsHelper.setTokens(tokens!!)
        }.getOrNull()
    }

    operator fun invoke(): Flow<Resource<Tokens>> = flow {
        try{
            emit(Resource.Success(refreshTokensOrNull()!!))
        }
        catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: ConstantsError.ERROR_OCCURRED))
        }catch (e: IOException){
            emit(Resource.Error(message = ConstantsError.NETWORK_ERROR))
        }
    }
}