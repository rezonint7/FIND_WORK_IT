package com.example.find_work_it

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.presentation.navigation.SetUpNavController
import com.example.find_work_it.presentation.screens.AddBasicTextField
import com.example.find_work_it.presentation.screens.BottomNavigationMenu
import com.example.find_work_it.ui.theme.BasicTextFieldStyle
import com.example.find_work_it.ui.theme.FINDWORKIT_Theme
import com.example.find_work_it.ui.theme.MainTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FINDWORKIT_Theme {
                // A surface container using the 'background' color from the theme
                rememberSystemUiController().setSystemBarsColor(MainTheme.colors.primaryBackground)

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
                    NavScreens.MainScreen.route,
                    NavScreens.FavoritesScreen.route,
                    NavScreens.ResponsesVacancyScreen.route -> true
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
                    backgroundColor = MainTheme.colors.primaryBackground
                ){
                    Log.d("MainActivity", "шатаап")
                    SetUpNavController(controller = navController)
                }
            }
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