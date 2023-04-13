package com.example.find_work_it.presentation.navigation


sealed class NavScreens (val route: String){
    object SplashScreen : NavScreens("splash_screen")
    object MainScreen : NavScreens("main_screen")
    object VacancyDetailScreen : NavScreens("vacancy_detail_screen")
    object AuthorizationScreen : NavScreens("authorization_screen")

    /*TODO: AuthorizationScreen,
    *       SettingsScreen,
    *       ProfileScreen и тд
    *
     */
}