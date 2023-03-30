package com.example.find_work_it.common.autorization

import android.net.Uri
import net.openid.appauth.AuthorizationServiceConfiguration

object AuthorizationConfig {
    val authorizationServiceConfiguration = AuthorizationServiceConfiguration(
        Uri.parse("https://example.com/oauth2/authorize"),
        Uri.parse("https://example.com/oauth2/token")
    )
}