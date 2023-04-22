package com.example.find_work_it

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.find_work_it.common.autorization.AuthorizationServiceApp
import com.example.find_work_it.common.autorization.model.Tokens
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.presentation.navigation.SetUpNavController
import com.example.find_work_it.presentation.screens.AddBasicTextField
import com.example.find_work_it.presentation.screens.BottomNavigationMenu
import com.example.find_work_it.presentation.screens.FilterButton
import com.example.find_work_it.presentation.screens.main_screen.TopBar
import com.example.find_work_it.ui.theme.BasicTextFieldStyle
import com.example.find_work_it.ui.theme.FINDWORKIT_Theme
import com.example.find_work_it.ui.theme.MainTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FINDWORKIT_Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MainTheme.colors.primaryBackground
                ) {
                    RootScreen()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RootScreen(){
    val navController = rememberNavController()
    var showBottomBar by remember { mutableStateOf(true) }
    var showTopBar by remember { mutableStateOf(true) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    showBottomBar = when (navBackStackEntry?.destination?.route) {
        NavScreens.MainScreen.route,
        NavScreens.FavoritesScreen.route,
        NavScreens.ResponsesVacancyScreen.route,
        NavScreens.ProfileScreen.route -> true
        else -> false
    }
    showTopBar = when(navBackStackEntry?.destination?.route){
        NavScreens.MainScreen.route -> true
        else -> false
    }
    val listNavigationBottomMenu = listOf(
        NavScreens.MainScreen,
        NavScreens.FavoritesScreen,
        NavScreens.ResponsesVacancyScreen,
        NavScreens.ProfileScreen
    )
    Scaffold(
        bottomBar = { if (showBottomBar) BottomNavigationMenu(navController, listNavigationBottomMenu) },
        topBar = {if(showTopBar) TopBar()},
        backgroundColor = MainTheme.colors.primaryBackground
    ){
        SetUpNavController(controller = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FINDWORKIT_Theme(darkTheme = true) {
        Surface(modifier = Modifier.background(MainTheme.colors.primaryBackground)) {
            RootScreen()
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