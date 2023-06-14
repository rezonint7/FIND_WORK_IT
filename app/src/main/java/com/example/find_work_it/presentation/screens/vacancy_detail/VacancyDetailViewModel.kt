package com.example.find_work_it.presentation.screens.vacancy_detail

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.domain.use_case.favorites_vacancies.DeleteFavoritesVacanciesUseCase
import com.example.find_work_it.domain.use_case.favorites_vacancies.GetFavoritesVacanciesUseCase
import com.example.find_work_it.domain.use_case.favorites_vacancies.PutFavoritesVacanciesUseCase
import com.example.find_work_it.domain.use_case.get_vacansies.GetSimilarVacanciesUseCase
import com.example.find_work_it.domain.use_case.get_vacansies.GetVacancyDetailUseCase
import com.example.find_work_it.domain.use_case.responses.PostResponseUseCase
import com.example.find_work_it.domain.use_case.resumes.GetSuitableResumesUseCase
import com.example.find_work_it.domain.use_case.resumes.GetUserResumesUseCase
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
    private val getSuitableResumesUseCase: GetSuitableResumesUseCase,
    private val getUserResumesUseCase: GetUserResumesUseCase,
    private val postResponseUseCase: PostResponseUseCase,
    private val getSimilarVacanciesUseCase: GetSimilarVacanciesUseCase,
    savedStateHandle: SavedStateHandle) : ViewModel(){
    private val _state = mutableStateOf<VacancyDetailState>(VacancyDetailState())
    private val _stateGet = mutableStateOf<FavoritesScreenState>(FavoritesScreenState())
    private val _stateSimilarVacancies = mutableStateOf<SimilarVacanciesState>(SimilarVacanciesState())
    private val _suitableResumes = mutableStateOf<GetSuitableResumesState>(GetSuitableResumesState())
    private val _getResumesUser = mutableStateOf<GetResumesUserState>(GetResumesUserState())
    private val _responseVacancy = mutableStateOf<ResponseVacancyUserState>(ResponseVacancyUserState())

    val state: State<VacancyDetailState> = _state
    val stateGetFavorite: State<FavoritesScreenState> = _stateGet
    val stateSimilarVacancies: State<SimilarVacanciesState> = _stateSimilarVacancies
    val suitableResumes: State<GetSuitableResumesState> = _suitableResumes

    val responseVacancy: State<ResponseVacancyUserState> = _responseVacancy
    val resumes: State<GetResumesUserState> = _getResumesUser

    init{
        savedStateHandle.get<String>(Constants.PARAM_VACANCY_ID)?.let { vacancyId ->
            getVacancy(vacancyId)
            getSimilarVacancies(vacancyId)
        }
        getFavoritesVacancies()
        getResumes()
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
        }.launchIn(viewModelScope)
    }

    private fun getSimilarVacancies(vacancyId : String){
        getSimilarVacanciesUseCase(vacancyId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _stateSimilarVacancies.value = SimilarVacanciesState(vacancies = result.data!!.toMutableList())
                }
                is Resource.Error -> {
                    _stateSimilarVacancies.value = SimilarVacanciesState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    //не используется, метод должен получать резюме которыми можно откликнуться, список всегда пуст
    private fun getSuitableResumes(vacancyId: String){
        getSuitableResumesUseCase(vacancyId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _suitableResumes.value = GetSuitableResumesState(resumes = result.data!!)
                }
                is Resource.Error -> {
                    _suitableResumes.value = GetSuitableResumesState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
        Log.d("sui", _suitableResumes.value.resumes.toString())
    }
    private fun getResumes(){
        getUserResumesUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _getResumesUser.value = GetResumesUserState(resumes = result.data!!)
                }
                is Resource.Error -> {
                    _getResumesUser.value = GetResumesUserState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
        Log.d("sui", _getResumesUser.value.resumes.toString())
    }

    fun responseVacancy(resumeId: String, vacancyId: String){
        postResponseUseCase(resumeId, vacancyId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _responseVacancy.value = ResponseVacancyUserState(success = result.data == true)
                }
                is Resource.Error -> {
                    _responseVacancy.value = ResponseVacancyUserState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}