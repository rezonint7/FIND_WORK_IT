package com.example.find_work_it.presentation.screens.main_screen

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.Resource
import com.example.find_work_it.common.SharedPrefsConstants
import com.example.find_work_it.common.autorization.model.Tokens
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO
import com.example.find_work_it.domain.repository.SharedPrefsRepository
import com.example.find_work_it.domain.use_case.get_vacansies.GetExtraVacanciesUseCase
import com.example.find_work_it.domain.use_case.get_vacansies.GetVacanciesUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val getExtraVacanciesUseCase: GetExtraVacanciesUseCase,
    private val sharedPreferencesRepository: SharedPrefsRepository) : ViewModel(){
    private val _state = mutableStateOf<MainScreenState>(MainScreenState())
    private val _tokens = MutableStateFlow<Tokens?>(null)
    private val _extraState = mutableStateOf(MainExtraScreenState())

    @SuppressLint("MutableCollectionMutableState")
    private val _pages = mutableStateOf<MutableMap<String, Int>>(mutableMapOf(
        "page" to 0,
        "pages" to 0
    ))

    val state: State<MainScreenState> = _state
    val tokens: StateFlow<Tokens?> = _tokens.asStateFlow()
    val extra: State<MainExtraScreenState> = _extraState

    init{
        getSharedPrefsTokens()
        getVacancies()
    }

    private fun getVacancies(){
        getVacanciesUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = MainScreenState(vacancies = (result.data ?: emptyList()).toMutableList())
                    _pages.value.putAll(mutableMapOf(
                        "pages" to _state.value.vacancies.last().pages,
                        "page" to _state.value.vacancies.last().page
                    ))
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

     fun getExtraVacancies(){
        val page = _pages.value.getValue("page") + 1
        getExtraVacanciesUseCase(page.toString()).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _extraState.value = MainExtraScreenState(vacancies = (result.data ?: emptyList()).toMutableList())
                    _state.value.vacancies.addAll(_extraState.value.vacancies)
                    _pages.value.putAll(mutableMapOf(
                        "pages" to _state.value.vacancies.last().pages,
                        "page" to _state.value.vacancies.last().page
                    ))
                }
                is Resource.Error -> {
                    _extraState.value = MainExtraScreenState(error = result.message ?: "Произошла ошибка")
                }
                is Resource.Loading -> {
                    _extraState.value = MainExtraScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSharedPrefsTokens(){
        viewModelScope.launch {
            _tokens.value = withContext(Dispatchers.IO) {
                sharedPreferencesRepository.getTokens()
            }
        }
    }
}