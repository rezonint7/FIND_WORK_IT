package com.example.find_work_it.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.find_work_it.common.autorization.AuthorizationServiceApp
import com.example.find_work_it.presentation.screens.authorization_screen.AuthorizationScreen
import com.example.find_work_it.presentation.screens.main_screen.MainScreen
import com.example.find_work_it.presentation.screens.splash_screen.SplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import net.openid.appauth.AuthorizationService


@Composable
fun SetUpNavController(
    controller: NavHostController,
    context:Context,
    authorizationService: AuthorizationService,
    authorizationServiceApp: AuthorizationServiceApp
){
    NavHost(navController = controller, startDestination = NavScreens.SplashScreen.route){
        composable(NavScreens.MainScreen.route){
            MainScreen()
        }
        composable(NavScreens.SplashScreen.route){
            SplashScreen(controller, context)
        }
        composable(NavScreens.AuthorizationScreen.route){
            AuthorizationScreen(controller = controller, context = context, authorizationService = authorizationService, authorizationServiceApp = authorizationServiceApp)
        }
    }
}