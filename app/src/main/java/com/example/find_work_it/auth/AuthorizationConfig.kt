package com.example.find_work_it.auth

import android.net.Uri
import com.example.find_work_it.common.Constants
import net.openid.appauth.AuthorizationServiceConfiguration

object AuthorizationConfig {
    val authorizationServiceConfiguration = AuthorizationServiceConfiguration(
        Uri.parse(Constants.AUTHORIZATION_ENDPOINT),
        Uri.parse(Constants.TOKEN_ENDPOINT)
    )
}