package com.example.find_work_it.presentation.screens.authorization_screen

data class AuthorizationScreenState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)
