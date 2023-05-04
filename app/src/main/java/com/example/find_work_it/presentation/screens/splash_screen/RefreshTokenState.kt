package com.example.find_work_it.presentation.screens.splash_screen

import com.example.find_work_it.common.autorization.model.Tokens

data class RefreshTokenState(
    val success: Boolean = false,
    val error: String = ""
)