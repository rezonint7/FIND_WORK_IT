package com.example.find_work_it.auth.model

data class Tokens(
    val access_token: String?,
    val expires_in: Int?,
    val refresh_token: String?,
    val token_type: String?
)