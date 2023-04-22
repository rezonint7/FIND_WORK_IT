package com.example.find_work_it.data.shared_prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.find_work_it.MainActivity
import com.example.find_work_it.common.SharedPrefsConstants
import com.example.find_work_it.common.autorization.model.Tokens
import com.google.gson.Gson

class SharedPrefsHelper {
    companion object{
        private val context: Context get() = MainActivity().applicationContext
        private val sharedPrefs = getInstance(context)
        private fun getInstance(context: Context) : SharedPreferences{
            return context.getSharedPreferences(SharedPrefsConstants.APP_NAME_SHAREDPREFS, Context.MODE_PRIVATE)
        }

        fun setTokens(tokens: Tokens){
            sharedPrefs.edit().putString(SharedPrefsConstants.JSON_TOKENS, Gson().toJson(tokens)).apply()
        }

        fun getTokens() : Tokens?{
            val json = sharedPrefs.getString(SharedPrefsConstants.JSON_TOKENS, "")
            return Gson().fromJson(json, Tokens::class.java) ?: null
        }

        fun containsTokens() : Boolean{
            return sharedPrefs.contains(SharedPrefsConstants.JSON_TOKENS)
        }
    }
}