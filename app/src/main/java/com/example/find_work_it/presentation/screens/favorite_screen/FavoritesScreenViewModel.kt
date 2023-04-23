package com.example.find_work_it.presentation.screens.favorite_screen

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.Resource
import com.example.find_work_it.domain.repository.ApiRepository
import com.example.find_work_it.domain.use_case.favorites_vacancies.DeleteFavoritesVacanciesUseCase
import com.example.find_work_it.domain.use_case.favorites_vacancies.GetFavoritesVacanciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val getFavoritesVacanciesUseCase: GetFavoritesVacanciesUseCase,
    private val deleteFavoritesVacanciesUseCase: DeleteFavoritesVacanciesUseCase,
    private val apiRepository: ApiRepository
    ) : ViewModel() {
    private val _state = mutableStateOf<FavoritesScreenState>(FavoritesScreenState())
    private val _stateDelete = mutableStateOf<FavoritesAddScreenState>(FavoritesAddScreenState())
    @SuppressLint("MutableCollectionMutableState")
    private val _pages = mutableStateOf<MutableMap<String, Int>>(mutableMapOf(
        "page" to 0,
        "pages" to 0
    ))

    val state: State<FavoritesScreenState> = _state
    val stateDelete: State<FavoritesAddScreenState> = _stateDelete

    init {
        getFavoritesVacancies()
    }

    private fun getFavoritesVacancies(){
        getFavoritesVacanciesUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = FavoritesScreenState(vacancies = (result.data ?: emptyList()).toMutableList())
                    _pages.value.putAll(mutableMapOf(
                        "pages" to _state.value.vacancies.last().pages,
                        "page" to _state.value.vacancies.last().page
                    ))
                }
                is Resource.Error -> {
                    _state.value = FavoritesScreenState(error = result.message ?: "Произошла ошибка")
                }
                is Resource.Loading -> {
                    _state.value = FavoritesScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteFavoriteVacancy(vacancyId : String){
       deleteFavoritesVacanciesUseCase(vacancyId).onEach { result ->
           when(result){
               is Resource.Success -> {
                   _stateDelete.value = FavoritesAddScreenState(success = result.data ?: false)
               }
               is Resource.Error -> _stateDelete.value = FavoritesAddScreenState(error = result.message ?: "Произошла ошибка")
               else -> {}
           }
       }
    }
}