package com.example.find_work_it.presentation.screens.splash_screen

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.find_work_it.common.SharedPrefsConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(sharedPreferences: SharedPreferences) : ViewModel() {
    private val _tokens = mutableStateOf<Boolean>(false)
    val tokens: State<Boolean> = _tokens

    init {
        _tokens.value = sharedPreferences.contains(SharedPrefsConstants.JSON_TOKENS)
        Log.d("contains", _tokens.value.toString())
    }
}