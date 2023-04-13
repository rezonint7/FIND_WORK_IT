package com.example.find_work_it.presentation.screens.authorization_screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
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
            modifier = Modifier.fillMaxSize().background(Color.Green),
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient(){
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            super.onPageStarted(view, url, favicon)

                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            webViewRef.value?.let { authViewModel.onAuthorizationStart(it) }
                        }

                        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                            authViewModel.onTokens(request)
                            return authViewModel.state.value.tokens?.access_token != null
                        }
                    }
                }
            },
            update = {webView -> webViewRef.value = webView}
        )
        if (authViewModel.state.value.error != null) {
            val buttonModifier = Modifier.padding(all = 4.dp)
            Text(text = authViewModel.state.value.error!!, style = MainTheme.typography.headerText, color = MainTheme.colors.refusedColor)
            Spacer(modifier = Modifier.height(16.dp))
            ButtonElement(text = "Попробовать снова", modifier = buttonModifier, backgroundColor = MainTheme.colors.refusedColor) {
                authViewModel.retryAuthorization()
            }
        }
        if(authViewModel.state.value.tokens != null) controller.navigate(NavScreens.MainScreen.route)
    }

}