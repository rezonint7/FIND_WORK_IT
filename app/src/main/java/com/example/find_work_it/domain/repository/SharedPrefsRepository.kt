package com.example.find_work_it.domain.repository

import com.example.find_work_it.common.autorization.model.Tokens

interface SharedPrefsRepository {
    suspend fun setTokens(tokens: Tokens)
    suspend fun getTokens() : Tokens?
}