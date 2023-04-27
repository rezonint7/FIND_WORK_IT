package com.example.find_work_it.presentation.screens.favorite_screen

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.Resource
import com.example.find_work_it.domain.model.Vacancy
import com.example.find_work_it.domain.repository.ApiRepository
import com.example.find_work_it.domain.use_case.favorites_vacancies.DeleteFavoritesVacanciesUseCase
import com.example.find_work_it.domain.use_case.favorites_vacancies.GetFavoritesVacanciesUseCase
import com.example.find_work_it.domain.use_case.favorites_vacancies.PutFavoritesVacanciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val getFavoritesVacanciesUseCase: GetFavoritesVacanciesUseCase,
    private val deleteFavoritesVacanciesUseCase: DeleteFavoritesVacanciesUseCase,
    private val putFavoritesVacanciesUseCase: PutFavoritesVacanciesUseCase,
    ) : ViewModel() {
    private val _state = mutableStateOf<FavoritesScreenState>(FavoritesScreenState())
    private val _stateUpdate = mutableStateOf<FavoritesScreenState>(FavoritesScreenState())
    private val _statePut = mutableStateOf<FavoritesAddScreenState>(FavoritesAddScreenState())
    private val _stateDelete = mutableStateOf<FavoritesAddScreenState>(FavoritesAddScreenState())
    @SuppressLint("MutableCollectionMutableState")
    private val _pages = mutableStateOf<MutableMap<String, Int>>(mutableMapOf(
        "page" to 0,
        "pages" to 0
    ))

    val state: State<FavoritesScreenState> = _state
    val stateUpdate: State<FavoritesScreenState> = _stateUpdate
    val statePutFavorite: State<FavoritesAddScreenState> = _statePut
    val stateDeleteFavorite: State<FavoritesAddScreenState> = _stateDelete

    init {
        getFavoritesVacancies()
    }

    fun getFavoritesVacancies(){
        getFavoritesVacanciesUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = FavoritesScreenState(vacancies = (result.data ?: emptyList()).toMutableList())
                    if(!result.data.isNullOrEmpty()){
                        _pages.value.putAll(mutableMapOf(
                            "pages" to _state.value.vacancies.last().pages,
                            "page" to _state.value.vacancies.last().page
                        ))
                    }
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
    fun updateFavoritesVacancies(){
        _state.value.vacancies.clear()
        getFavoritesVacanciesUseCase().onEach{ result ->
            when(result){
                is Resource.Success -> {
                    _stateUpdate.value = FavoritesScreenState(vacancies = (result.data ?: emptyList()).toMutableList())
                    _state.value.vacancies.addAll(_stateUpdate.value.vacancies)
                    if(!result.data.isNullOrEmpty()){
                        _pages.value.putAll(mutableMapOf(
                            "pages" to _state.value.vacancies.last().pages,
                            "page" to _state.value.vacancies.last().page
                        ))
                    }
                }
                is Resource.Error -> {
                    _stateUpdate.value = FavoritesScreenState(error = result.message ?: "Произошла ошибка")
                }
                is Resource.Loading -> {
                    _stateUpdate.value = FavoritesScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun putFavoriteVacancy(vacancyId : String){
        putFavoritesVacanciesUseCase(vacancyId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _statePut.value = FavoritesAddScreenState(success = result.data ?: false)
                }
                is Resource.Error -> _statePut.value = FavoritesAddScreenState(error = result.message ?: "Произошла ошибка")
                else -> {}
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
            _state.value.vacancies.removeIf { it.idVacancy == vacancyId }
        }.launchIn(viewModelScope)
    }
}