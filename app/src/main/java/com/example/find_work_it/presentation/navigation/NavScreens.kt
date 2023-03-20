package com.example.find_work_it.presentation.navigation

import com.example.find_work_it.R

sealed class NavScreens (val route: String){
    object SplashScreen : NavScreens("splash_screen")
    object MainScreen : NavScreens("main_screen")

    /*TODO: AuthorizationScreen,
    *       SettingsScreen,
    *       ProfileScreen и тд
    *
     */
}