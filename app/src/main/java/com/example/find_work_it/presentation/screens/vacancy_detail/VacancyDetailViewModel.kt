package com.example.find_work_it.presentation.screens.vacancy_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.Resource
import com.example.find_work_it.domain.repository.ApiRepository
import com.example.find_work_it.domain.use_case.get_vacansies.GetVacanciesUseCase
import com.example.find_work_it.domain.use_case.get_vacansies.GetVacancyDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VacancyDetailViewModel @Inject constructor(
    private val getVacancyDetailUseCase: GetVacancyDetailUseCase,
    private val apiRepository: ApiRepository,
    savedStateHandle: SavedStateHandle
    ) : ViewModel(){
    private val _state = mutableStateOf<VacancyDetailState>(VacancyDetailState())
    val state: State<VacancyDetailState> = _state

    init{
        savedStateHandle.get<String>(Constants.PARAM_VACANCY_ID)?.let { vacancyId ->
            getVacancy(vacancyId)
        }
    }

    private fun getVacancy(vacancyId: String){
        getVacancyDetailUseCase(vacancyId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = VacancyDetailState(vacancy = result.data)
                }
                is Resource.Error -> {
                    _state.value = VacancyDetailState(error = result.message ?: "Произошла ошибка")
                }
                is Resource.Loading -> {
                    _state.value = VacancyDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun putFavoriteVacancy(vacancyId : String) : Boolean{
        return try {
            viewModelScope.launch {
                apiRepository.putFavoriteVacancy(vacancyId)
            }
            true
        }catch (ex: Exception){
            false
        }
    }
}