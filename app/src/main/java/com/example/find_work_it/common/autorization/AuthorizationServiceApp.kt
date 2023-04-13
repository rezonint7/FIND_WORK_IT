package com.example.find_work_it.common.autorization

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import androidx.core.content.ContextCompat
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.autorization.model.Tokens
import net.openid.appauth.*
import okhttp3.*
import java.io.IOException

class AuthorizationServiceApp {
    private val redirectUri = Uri.parse(Constants.REDIRECT_URI)
    private var authorizationRequestCode : AuthorizationRequest? = null
    fun startAuthorization(webView: WebView){
        authorizationRequestCode = AuthorizationRequest.Builder(
            AuthorizationConfig.authorizationServiceConfiguration,
            Constants.CLIENT_ID,
            ResponseTypeValues.CODE,
            redirectUri
        ).build()
        webView.loadUrl(authorizationRequestCode!!.toUri().toString())
    }

    fun getAccessUserToken(
        url: String,
        authorizationService: AuthorizationService,
        onTokenExchangeComplete: (Tokens?) -> Unit
    ){
        val responseCode = AuthorizationResponse.Builder(authorizationRequestCode!!).fromUri(Uri.parse(url)).build()
        val tokenRequest = TokenRequest.Builder(
            AuthorizationConfig.authorizationServiceConfiguration,
            Constants.CLIENT_ID
        )
            .setAuthorizationCode(responseCode.authorizationCode!!)
            .setGrantType(GrantTypeValues.AUTHORIZATION_CODE)
            .setRedirectUri(redirectUri)
            .setAdditionalParameters(
                mapOf(
                    "client_secret" to Constants.CLIENT_SECRET,
                    "Content-Type" to "application/x-www-form-urlencoded"
                )
            ).build()
        authorizationService.performTokenRequest(tokenRequest){ response, _ ->
            onTokenExchangeComplete(
                 Tokens(
                    response?.accessToken,
                    response?.accessTokenExpirationTime,
                    response?.refreshToken,
                    response?.tokenType
                )
            )
        }
    }

//    fun performTokenExchangeRequest(
//        authorizationService: AuthorizationService,
//        authorizationResponse: AuthorizationResponse,
//        tokenRequest: TokenRequest,
//        onTokenExchangeComplete: (TokenResponse?, AuthorizationException?) -> Unit
//    ){
//        val requestBody = tokenRequest.configuration.toJsonString()
//        val body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), requestBody)
//        val request = Request.Builder()
//            .url(tokenRequest.configuration.tokenEndpoint.toString())
//            .post(body) // add body
//            .addHeader("Content-Type", "application/x-www-form-urlencoded")
//            .addHeader()
//            .build()
//        val client = OkHttpClient().newCall(request)
//        client.enqueue(object : Callback{
//            override fun onFailure(call: Call, e: IOException) {
//                onTokenExchangeComplete(null, AuthorizationException.fromTemplate(
//                    AuthorizationException.GeneralErrors.NETWORK_ERROR, e
//                ))
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val tokenResponse = response.body()?.string()?.let { TokenResponse.jsonDeserialize(it) }
//                onTokenExchangeComplete(tokenResponse, null)
//            }
//        })
//    }
}