package com.example.find_work_it.data.repository

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import com.example.find_work_it.common.SharedPrefsConstants
import com.example.find_work_it.common.autorization.model.Tokens
import com.example.find_work_it.domain.repository.SharedPrefsRepository
import com.google.gson.Gson
import javax.inject.Inject

class SharedPrefsRepositoryImpl @Inject constructor(private val sharedPrefs: SharedPreferences) : SharedPrefsRepository {
    @SuppressLint("CommitPrefEdits")
    override suspend fun setTokens(tokens: Tokens) {
        Log.d("SET", tokens.access_token.toString())
        Log.d("SET", tokens.expires_in.toString())

        sharedPrefs.edit().putString(SharedPrefsConstants.JSON_TOKENS, Gson().toJson(tokens)).apply()
    }

    override suspend fun getTokens(): Tokens? {
        val json = sharedPrefs.getString(SharedPrefsConstants.JSON_TOKENS, "")
        return Gson().fromJson(json, Tokens::class.java) ?: null
    }
}