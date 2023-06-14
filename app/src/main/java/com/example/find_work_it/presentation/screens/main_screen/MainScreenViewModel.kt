package com.example.find_work_it.presentation.screens.main_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.domain.use_case.get_vacansies.GetExtraVacanciesUseCase
import com.example.find_work_it.domain.use_case.get_vacansies.GetVacanciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val getExtraVacanciesUseCase: GetExtraVacanciesUseCase) : ViewModel(){
    private val _state: MutableLiveData<MainScreenState> = MutableLiveData<MainScreenState>(MainScreenState())
    private val _extraState = mutableStateOf(MainExtraScreenState())

    @Suppress("MutableCollectionMutableState")
    private val _pages = mutableStateOf<MutableMap<String, Int>>(mutableMapOf(
        "page" to 0,
        "pages" to 0
    ))

    val state: MutableLiveData<MainScreenState> = _state
    val extra: State<MainExtraScreenState> = _extraState

    init{
        getVacancies()
    }

    fun getVacancies(text: String = ""){
        getVacanciesUseCase(text).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = MainScreenState(vacancies = (result.data ?: emptyList()).toMutableList())
                    if(!result.data.isNullOrEmpty()){
                        _pages.value.putAll(mutableMapOf(
                            "pages" to _state.value!!.vacancies.last().pages,
                            "page" to _state.value!!.vacancies.last().page
                        ))
                    }
                }
                is Resource.Error -> {
                    _state.value = MainScreenState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                is Resource.Loading -> {
                    _state.value = MainScreenState(isLoading = true)
                }
            }
            Log.d("MAIN", "пизда")
        }.launchIn(viewModelScope)
    }

     fun getExtraVacancies(text: String = ""){
        val page = _pages.value.getValue("page") + 1
        getExtraVacanciesUseCase(page.toString(), text).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _extraState.value = MainExtraScreenState(vacancies = (result.data ?: emptyList()).toMutableList())
                    _state.value?.vacancies?.addAll(_extraState.value.vacancies)
                    if(!result.data.isNullOrEmpty()){
                        _pages.value.putAll(mutableMapOf(
                            "pages" to _state.value!!.vacancies.last().pages,
                            "page" to _state.value!!.vacancies.last().page
                        ))
                    }
                }
                is Resource.Error -> {
                    _extraState.value = MainExtraScreenState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                is Resource.Loading -> {
                    _extraState.value = MainExtraScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}