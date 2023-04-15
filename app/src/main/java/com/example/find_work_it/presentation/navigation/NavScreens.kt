package com.example.find_work_it.presentation.navigation

import com.example.find_work_it.R


sealed class NavScreens (val route: String, val icon: Int = -1, var title: Int = -1){
    object SplashScreen : NavScreens("splash_screen")
    object VacancyDetailScreen : NavScreens("vacancy_detail_screen")
    object AuthorizationScreen : NavScreens("authorization_screen")

    object MainScreen : NavScreens("main_screen", R.drawable.round_home_screen_icon, R.string.home_screen)
    object FavoritesScreen : NavScreens("favorites_screen", R.drawable.round_favorite_screen_icon, R.string.favorite_screen)
    object ResponsesVacancyScreen : NavScreens("responses_vacancy_screen", R.drawable.round_favorite_screen_icon, R.string.responses_screen)
    object ProfileScreen : NavScreens("profile_screen", R.drawable.round_profile_screen_icon, R.string.profile_screen)


    /*TODO: AuthorizationScreen,
    *       SettingsScreen,
    *       ProfileScreen и тд
    *
     */
}