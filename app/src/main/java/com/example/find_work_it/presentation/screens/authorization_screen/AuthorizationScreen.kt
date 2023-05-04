package com.example.find_work_it.presentation.screens.authorization_screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.node.Ref
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.presentation.screens.ButtonElement
import com.example.find_work_it.presentation.screens.ErrorUseCaseElement
import com.example.find_work_it.presentation.screens.LoadingUseCaseElement
import com.example.find_work_it.ui.theme.MainTheme


@SuppressLint("SetJavaScriptEnabled", "CommitPrefEdits")
@Composable
fun AuthorizationScreen(
    controller: NavController,
    authViewModel: AuthorizationScreenViewModel = hiltViewModel()
){
    val webViewRef = remember { Ref<WebView>() }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green),
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient(){
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            super.onPageStarted(view, url, favicon)
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            view?.loadData("<html><body><h1>No internet connection</h1></body></html>", "text/html", null)
                        }

                        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                            authViewModel.onTokens(request)
                            Log.d("URL2", request!!.url.toString())
                            return authViewModel.state.value.success
                        }
                    }
                    loadUrl(authViewModel.authorizationRequest.value!!.toUri().toString())
                }
            },
            update = {authViewModel.authorizationRequest.value!!.toUri().toString()}
        )
        if(authViewModel.state.value.success) controller.navigate(NavScreens.MainScreen.route)
        if (authViewModel.state.value.error != null) {
            ErrorUseCaseElement(error = authViewModel.state.value.error.toString()) {
                authViewModel.retryAuthorization()
            }
        }
    }

}