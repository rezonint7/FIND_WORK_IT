package com.example.find_work_it.presentation.screens.profile_screen

import com.example.find_work_it.domain.model.User

data class ProfileScreenState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)
