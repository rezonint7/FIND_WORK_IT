package com.example.find_work_it.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.find_work_it.Greeting
import com.example.find_work_it.presentation.screens.main_screen.MainScreen
import com.example.find_work_it.presentation.screens.splash_screen.SplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SetUpNavController(controller: NavHostController){
    val systemUiController = rememberSystemUiController()
    NavHost(navController = controller, startDestination = NavScreens.SplashScreen.route){
        composable(NavScreens.MainScreen.route){
            MainScreen()
        }
        composable(NavScreens.SplashScreen.route){
            SplashScreen(controller)
        }
    }
}