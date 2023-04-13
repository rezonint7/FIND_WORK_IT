package com.example.find_work_it.presentation.screens.main_screen

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.Resource
import com.example.find_work_it.common.SharedPrefsConstants
import com.example.find_work_it.common.autorization.model.Tokens
import com.example.find_work_it.domain.repository.SharedPrefsRepository
import com.example.find_work_it.domain.use_case.get_vacansies.GetVacanciesUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val sharedPreferencesRepository: SharedPrefsRepository) : ViewModel(){
    private val _state = mutableStateOf<MainScreenState>(MainScreenState())
    private val _tokens = mutableStateOf<Tokens?>(null)

    val state: State<MainScreenState> = _state
    val tokens: State<Tokens?> = _tokens

    init{
        getSharedPrefsTokens()
        getVacancies()
    }

    private fun getVacancies(){
        getVacanciesUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = MainScreenState(vacancies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = MainScreenState(error = result.message ?: "Произошла ошибка")
                }
                is Resource.Loading -> {
                    _state.value = MainScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSharedPrefsTokens(){
        viewModelScope.launch(Dispatchers.IO){
            _tokens.value = sharedPreferencesRepository.getTokens()
        }
    }
}