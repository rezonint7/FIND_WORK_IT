package com.example.find_work_it.presentation.screens.response_screen

import android.util.Log
import android.webkit.WebResourceRequest
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.Resource
import com.example.find_work_it.auth.AuthorizationServiceApp
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.domain.use_case.authorization.AuthorizationUseCase
import com.example.find_work_it.domain.use_case.responses.GetResponsesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.openid.appauth.AuthorizationRequest
import javax.inject.Inject

@HiltViewModel
class ResponseVacancyScreenViewModel @Inject constructor(
    private val getResponsesUseCase: GetResponsesUseCase
): ViewModel() {
    private val _state = mutableStateOf<ResponseVacancyScreenState>(ResponseVacancyScreenState())
    val state: State<ResponseVacancyScreenState> = _state

    init {
        getResponses()
    }

    private fun getResponses(){
        getResponsesUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = ResponseVacancyScreenState(responses = result.data!!)
                }
                is Resource.Loading -> {
                    _state.value = ResponseVacancyScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = ResponseVacancyScreenState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
            }
        }.launchIn(viewModelScope)
    }

}