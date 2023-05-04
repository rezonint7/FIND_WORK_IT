package com.example.find_work_it.domain.model

import com.example.find_work_it.data.remote.dto.user.models.ProfileVideos

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val middleName: String? = "",
    val phone: String? = "",
    val email: String? = "",
    val isInSearch: Boolean,
    val profileVideos: ProfileVideos? = null
)