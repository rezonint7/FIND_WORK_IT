package com.example.find_work_it.presentation.screens.authorization_screen

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.autorization.AuthorizationServiceApp
import com.example.find_work_it.presentation.navigation.NavScreens
import com.google.gson.Gson
import net.openid.appauth.AuthorizationService

@SuppressLint("SetJavaScriptEnabled", "CommitPrefEdits")
@Composable
fun AuthorizationScreen(
    context: Context,
    controller: NavController,
    authorizationService: AuthorizationService,
    authorizationServiceApp: AuthorizationServiceApp
){
    val sharedPreferences = context.getSharedPreferences("FIND_WORK_IT", Context.MODE_PRIVATE)
    val sharedPreferencesEdit = sharedPreferences.edit()
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = object : WebViewClient(){
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)

                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)

                    }

                    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                        val url = request?.url.toString()
                        if (request != null && url.startsWith(Constants.REDIRECT_URI)) {
                            authorizationServiceApp.getAccessUserToken(url, authorizationService){ tokens, authorizationException ->
                                val json = Gson().toJson(tokens)
                                sharedPreferencesEdit.putString("jsonTokens", json)
                                Log.d("APP123", json)
                                controller.navigate(NavScreens.MainScreen.route)
                            }

                            return true
                        }
                        else{
                            //exception
                        }
                        return false
                    }
                }
            }
        },
        update = {webview -> authorizationServiceApp.startAuthorization(webview)}
    )
}