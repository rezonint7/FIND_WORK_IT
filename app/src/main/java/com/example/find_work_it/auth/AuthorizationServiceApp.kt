package com.example.find_work_it.auth

import android.net.Uri
import com.example.find_work_it.common.Constants
import com.example.find_work_it.auth.model.Tokens
import net.openid.appauth.*

class AuthorizationServiceApp {
    private val redirectUri = Uri.parse(Constants.REDIRECT_URI)
    private var authorizationRequestCode : AuthorizationRequest? = null
    fun startAuthorization() : AuthorizationRequest?{
        authorizationRequestCode = AuthorizationRequest.Builder(
            AuthorizationConfig.authorizationServiceConfiguration,
            Constants.CLIENT_ID,
            ResponseTypeValues.CODE,
            redirectUri
        ).build()
        return authorizationRequestCode
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
                     response?.accessTokenExpirationTime?.toInt(),
                     response?.refreshToken,
                     response?.tokenType
                )
            )
        }
    }

    fun refreshAccessUserToken(
        refreshToken: String,
        authorizationService: AuthorizationService,
        onTokenExchangeComplete: (Tokens?) -> Unit
    ){
        val tokenRequest = TokenRequest.Builder(
            AuthorizationConfig.authorizationServiceConfiguration,
            Constants.CLIENT_ID
        )
            .setRefreshToken(refreshToken)
            .setGrantType(GrantTypeValues.REFRESH_TOKEN)
            .setRedirectUri(redirectUri)
            .setAdditionalParameters(
                mapOf(
                    "client_secret" to Constants.CLIENT_SECRET,
                    "Content-Type" to "application/x-www-form-urlencoded"
                )
            ).build()

        authorizationService.performTokenRequest(tokenRequest) { response, _ ->
            onTokenExchangeComplete(
                Tokens(
                    response?.accessToken,
                    response?.accessTokenExpirationTime?.toInt(),
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