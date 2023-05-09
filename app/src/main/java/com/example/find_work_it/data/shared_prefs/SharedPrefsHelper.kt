package com.example.find_work_it.data.shared_prefs

import android.content.Context
import android.util.Log
import com.example.find_work_it.common.SharedPrefsConstants
import com.example.find_work_it.auth.model.Tokens
import com.google.gson.Gson

class SharedPrefsHelper (context: Context) {
    private val sharedPrefs = context.getSharedPreferences(
        SharedPrefsConstants.APP_NAME_SHAREDPREFS, Context.MODE_PRIVATE
    )

    fun setTokens(tokens: Tokens) {
        sharedPrefs.edit().putString(SharedPrefsConstants.JSON_TOKENS, Gson().toJson(tokens)).apply()
    }

    fun getTokens(): Tokens? {
        val json = sharedPrefs.getString(SharedPrefsConstants.JSON_TOKENS, "")
        Log.d("TOKENS", json.toString())
        return Gson().fromJson(json, Tokens::class.java) ?: null
    }

    fun containsTokens(): Boolean {
        return sharedPrefs.contains(SharedPrefsConstants.JSON_TOKENS)
    }

    companion object {
        private var INSTANCE: SharedPrefsHelper? = null

        fun getInstance(context: Context): SharedPrefsHelper {
            if (INSTANCE == null) {
                INSTANCE = SharedPrefsHelper(context)
            }
            return INSTANCE!!
        }
    }
}