package com.example.find_work_it.presentation.screens.splash_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.domain.use_case.user.GetUserInfoUseCase
import com.example.find_work_it.domain.use_case.user.RefreshUserTokensUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val refreshUserTokensUseCase: RefreshUserTokensUseCase) : ViewModel() {
    private val _state = mutableStateOf<SplashScreenState>(SplashScreenState())
    private val _tokens = mutableStateOf<RefreshTokenState>(RefreshTokenState())

    val state: State<SplashScreenState> = _state
    val tokens: State<RefreshTokenState> = _tokens
    init {
        getUserInfo()
    }

    private fun getUserInfo(){
        getUserInfoUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = SplashScreenState(user = result.data)
                    Log.d("USER", _state.value.user.toString())
                }
                is Resource.Error -> {
                    _state.value = SplashScreenState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                    Log.d("USER", _state.value.error)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun refreshUserTokens(){
        refreshUserTokensUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _tokens.value = RefreshTokenState(result.data != null)
                }
                is Resource.Error -> {
                    _tokens.value = RefreshTokenState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}