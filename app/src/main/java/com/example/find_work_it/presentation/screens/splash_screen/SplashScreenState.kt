package com.example.find_work_it.presentation.screens.splash_screen

import com.example.find_work_it.domain.model.User

data class SplashScreenState(
    val user: User? = null,
    val error: String = ""
)
data class RefreshTokenState(
    val success: Boolean = false,
    val error: String = ""
)