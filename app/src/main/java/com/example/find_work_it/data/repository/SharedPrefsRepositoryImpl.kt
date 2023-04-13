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
        sharedPrefs.edit().apply{
            putString(SharedPrefsConstants.JSON_TOKENS, Gson().toJson(tokens))
        }
    }

    override suspend fun getTokens(): Tokens {
        val json = sharedPrefs.getString(SharedPrefsConstants.JSON_TOKENS, null)
        Log.d("APP123", json!!)
        return Gson().fromJson(json, Tokens::class.java)
    }
}