package com.example.find_work_it.presentation.screens.authorization_screen

import com.example.find_work_it.common.Resource
import com.example.find_work_it.common.autorization.model.Tokens

data class AuthorizationScreenState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)
