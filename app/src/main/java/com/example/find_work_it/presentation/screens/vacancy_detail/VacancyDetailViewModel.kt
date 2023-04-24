package com.example.find_work_it.presentation.screens.vacancy_detail

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.Resource
import com.example.find_work_it.domain.use_case.favorites_vacancies.DeleteFavoritesVacanciesUseCase
import com.example.find_work_it.domain.use_case.favorites_vacancies.GetFavoritesVacanciesUseCase
import com.example.find_work_it.domain.use_case.favorites_vacancies.PutFavoritesVacanciesUseCase
import com.example.find_work_it.domain.use_case.get_vacansies.GetVacancyDetailUseCase
import com.example.find_work_it.presentation.screens.favorite_screen.FavoritesAddScreenState
import com.example.find_work_it.presentation.screens.favorite_screen.FavoritesScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VacancyDetailViewModel @Inject constructor(
    private val getVacancyDetailUseCase: GetVacancyDetailUseCase,
    private val getFavoritesVacanciesUseCase: GetFavoritesVacanciesUseCase,
    private val deleteFavoritesVacanciesUseCase: DeleteFavoritesVacanciesUseCase,
    private val putFavoritesVacanciesUseCase: PutFavoritesVacanciesUseCase,
    savedStateHandle: SavedStateHandle
    ) : ViewModel(){
    private val _state = mutableStateOf<VacancyDetailState>(VacancyDetailState())
    private val _statePut = mutableStateOf<FavoritesAddScreenState>(FavoritesAddScreenState())
    private val _stateGet = mutableStateOf<FavoritesScreenState>(FavoritesScreenState())
    private val _stateDelete = mutableStateOf<FavoritesAddScreenState>(FavoritesAddScreenState())

    val state: State<VacancyDetailState> = _state
    val statePutFavorite: State<FavoritesAddScreenState> = _statePut
    val stateGetFavorite: State<FavoritesScreenState> = _stateGet
    val stateDeleteFavorite: State<FavoritesAddScreenState> = _stateDelete

    val isFavorited = mutableStateOf(false)
    init{
        savedStateHandle.get<String>(Constants.PARAM_VACANCY_ID)?.let { vacancyId ->
            getVacancy(vacancyId)
        }
        getFavoritesVacancies()
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

    private fun getFavoritesVacancies(){
        getFavoritesVacanciesUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _stateGet.value = FavoritesScreenState(vacancies = (result.data ?: emptyList()).toMutableList())
                    if(!result.data.isNullOrEmpty()){
//                        _pages.value.putAll(mutableMapOf(
//                            "pages" to _state.value.vacancies.last().pages,
//                            "page" to _state.value.vacancies.last().page
//                        ))
                    }
                }
                is Resource.Error -> {
                    _stateGet.value = FavoritesScreenState(error = result.message ?: "Произошла ошибка")
                }
                is Resource.Loading -> {
                    _stateGet.value = FavoritesScreenState(isLoading = true)
                }
            }
            if(_stateGet.value.error.isEmpty()){
                _stateGet.value.vacancies.forEach{
                    isFavorited.value = it.idVacancy == _state.value.vacancy?.idVacancy
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
        }.launchIn(viewModelScope)
    }
}