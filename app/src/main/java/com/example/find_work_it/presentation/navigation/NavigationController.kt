package com.example.find_work_it.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.find_work_it.presentation.screens.add_resume_screen.AddResumeScreen
import com.example.find_work_it.presentation.screens.authorization_screen.AuthorizationScreen
import com.example.find_work_it.presentation.screens.favorite_screen.FavoritesScreen
import com.example.find_work_it.presentation.screens.main_screen.MainScreen
import com.example.find_work_it.presentation.screens.profile_screen.ProfileScreen
import com.example.find_work_it.presentation.screens.splash_screen.SplashScreen
import com.example.find_work_it.presentation.screens.vacancy_detail.VacancyDetailScreen


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SetUpNavController(
    controller: NavHostController
){
    NavHost(navController = controller, startDestination = NavScreens.SplashScreen.route){
        composable(NavScreens.MainScreen.route){
            MainScreen(controller)
        }
        composable(NavScreens.SplashScreen.route){
            SplashScreen(controller)
        }
        composable(NavScreens.AuthorizationScreen.route){
            AuthorizationScreen(controller)
        }
        composable(NavScreens.VacancyDetailScreen.route + "/{vacancyId}"){
            VacancyDetailScreen(controller)
        }
        composable(NavScreens.FavoritesScreen.route){
            FavoritesScreen(controller)
        }
        composable(NavScreens.ProfileScreen.route){
            ProfileScreen(controller)
        }
        composable(NavScreens.AddResumeScreen.route + "/{resumeId}"){
            AddResumeScreen(controller)
        }
    }
}