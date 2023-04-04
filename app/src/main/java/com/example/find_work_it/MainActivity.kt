package com.example.find_work_it

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.find_work_it.common.autorization.AuthorizationServiceApp
import com.example.find_work_it.common.autorization.model.Tokens
import com.example.find_work_it.presentation.navigation.SetUpNavController
import com.example.find_work_it.presentation.screens.AddBasicTextField
import com.example.find_work_it.presentation.screens.FilterButton
import com.example.find_work_it.ui.theme.BasicTextFieldStyle
import com.example.find_work_it.ui.theme.FINDWORKIT_Theme
import com.example.find_work_it.ui.theme.MainTheme
import com.google.gson.Gson
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService

class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences =  getSharedPreferences("FIND_WORK_IT", Context.MODE_PRIVATE)
        setContent {
            FINDWORKIT_Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MainTheme.colors.primaryBackground
                ) {
                    val authorizationService = AuthorizationService(this)
                    val authorizationServiceApp = AuthorizationServiceApp()
                    val tokens = sharedPreferences.getString("jsonTokens", "")
                    Log.d("APP123-main", tokens!!)
                    SetUpNavController(controller = rememberNavController(), LocalContext.current, authorizationService, authorizationServiceApp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FINDWORKIT_Theme(darkTheme = true) {
        Surface(modifier = Modifier.background(MainTheme.colors.primaryBackground)) {
            Spacer(modifier = Modifier.width(width = 8.dp))
            AddBasicTextField(
                sizeWidth = 254,
                sizeHeight = 48,
                textStyle = BasicTextFieldStyle,
                placeholder = "Поиск",
                icon = Icons.Default.Search,
                iconContentDescription = "iconSearch"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    FINDWORKIT_Theme(darkTheme = false) {
        Surface() {
            Spacer(modifier = Modifier.width(width = 8.dp))
            AddBasicTextField(
                sizeWidth = 254,
                sizeHeight = 48,
                textStyle = BasicTextFieldStyle,
                placeholder = "Поиск",
                icon = Icons.Default.Search,
                iconContentDescription = "iconSearch"
            )
        }

    }
}