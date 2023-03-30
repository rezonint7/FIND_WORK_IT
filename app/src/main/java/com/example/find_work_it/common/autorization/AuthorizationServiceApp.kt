package com.example.find_work_it.common.autorization

import android.content.Context
import android.net.Uri
import androidx.core.content.ContextCompat
import com.example.find_work_it.BuildConfig
import com.example.find_work_it.common.Constants
import net.openid.appauth.*
import okhttp3.*
import java.io.IOException

class AuthorizationServiceApp {
    fun startAuthorization(context: Context){
        val redirectUri = Uri.parse(Constants.REDIRECT_URI)
        val authorizationRequest = AuthorizationRequest.Builder(
            AuthorizationConfig.authorizationServiceConfiguration,
            BuildConfig.CLIENT_ID,
            ResponseTypeValues.CODE,
            redirectUri
        ).build()

        val authorizationService = AuthorizationService(context)
        val authorizationIntent = authorizationService.getAuthorizationRequestIntent(authorizationRequest)

        ContextCompat.startActivity(context, authorizationIntent, null)
    }

    fun performTokenExchangeRequest(
        authorizationService: AuthorizationService,
        tokenRequest: TokenRequest,
        onTokenExchangeComplete: (TokenResponse?, AuthorizationException?) -> Unit
    ){
        val request = Request.Builder()
            .url(tokenRequest.configuration.tokenEndpoint.toString())
            .post() // add body
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build()
        val client = OkHttpClient().newCall(request)
        client.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                onTokenExchangeComplete(null, AuthorizationException.fromTemplate(
                    AuthorizationException.GeneralErrors.NETWORK_ERROR, e
                ))
            }

            override fun onResponse(call: Call, response: Response) {
                val tokenResponse = TokenResponse.fromJson(response.body()?.string())
                onTokenExchangeComplete(tokenResponse, null)
            }

        })
    }
}