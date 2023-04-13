package com.example.find_work_it.common.autorization.model

data class Tokens(
    val access_token: String?,
    val expires_in: Long?,
    val refresh_token: String?,
    val token_type: String?
)