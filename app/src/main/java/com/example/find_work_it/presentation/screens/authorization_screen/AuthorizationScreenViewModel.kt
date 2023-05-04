package com.example.find_work_it.presentation.screens.authorization_screen

import android.content.SharedPreferences
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.Resource
import com.example.find_work_it.common.SharedPrefsConstants
import com.example.find_work_it.common.autorization.AuthorizationServiceApp
import com.example.find_work_it.domain.use_case.authorization.AuthorizationUseCase
import com.example.find_work_it.presentation.screens.main_screen.MainScreenState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import javax.inject.Inject

@HiltViewModel
class AuthorizationScreenViewModel @Inject constructor(
    private val authorizationUseCase: AuthorizationUseCase,
    private val authorizationServiceApp: AuthorizationServiceApp): ViewModel() {
    private val _state = mutableStateOf<AuthorizationScreenState>(AuthorizationScreenState())
    private val _authorizationRequest = mutableStateOf<AuthorizationRequest?>(null)
    val state: State<AuthorizationScreenState> = _state
    val authorizationRequest: State<AuthorizationRequest?> = _authorizationRequest

    init {
        onAuthorizationStart()
    }

    private fun onAuthorizationStart() {
        _authorizationRequest.value = authorizationServiceApp.startAuthorization()
    }

    fun onTokens(request: WebResourceRequest?){
        authorizationUseCase(request).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = AuthorizationScreenState(success = result.data != null)
                }
                is Resource.Error -> {
                    _state.value = AuthorizationScreenState(error = result.message ?: Constants.ERROR_OCCURRED)
                }
                is Resource.Loading -> {
                    _state.value = AuthorizationScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun retryAuthorization() {
        _state.value = AuthorizationScreenState()
    }
}