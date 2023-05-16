package com.example.find_work_it.presentation.screens.add_resume_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.domain.use_case.dictionaries.GetDictionariesUseCase
import com.example.find_work_it.domain.use_case.resumes.GetUserResumeDetailUseCase
import com.example.find_work_it.domain.use_case.suggest.GetPositionsResumeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddResumeScreenViewModel @Inject constructor(
    private val getUserResumeDetailUseCase: GetUserResumeDetailUseCase,
    private val getDictionariesUseCase: GetDictionariesUseCase,
    private val getPositionsResumeUseCase: GetPositionsResumeUseCase,
    savedStateHandle: SavedStateHandle): ViewModel() {
    private val _state = mutableStateOf<AddResumeScreenState>(AddResumeScreenState())
    private val _dictionaries = mutableStateOf<DictionariesState>(DictionariesState())
    private val _suggestPosition: MutableLiveData<SuggestPositionState> = MutableLiveData(
        SuggestPositionState()
    )

    val state: State<AddResumeScreenState> = _state
    val suggestPositions: MutableLiveData<SuggestPositionState> = _suggestPosition
    val dictionariesState: State<DictionariesState> = _dictionaries
    init {
        savedStateHandle.get<String>(Constants.PARAM_RESUME_ID)?.let{ resumeId ->
            getResume(resumeId)
        }
        getDictionaries()
    }

    private fun getResume(resumeId: String){
        getUserResumeDetailUseCase(resumeId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = AddResumeScreenState(resume = result.data)
                }
                is Resource.Loading -> {
                    _state.value = AddResumeScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = AddResumeScreenState(error = result.message ?: "Произошла ошибка")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getDictionaries(){
        getDictionariesUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _dictionaries.value = DictionariesState(dictionaries = result.data)
                }
                is Resource.Error -> {
                    _dictionaries.value = DictionariesState(error = result.message ?: "Произошла ошибка")
                }
                else -> {}
            }

        }.launchIn(viewModelScope)
    }

    fun getSuggestsPosition(text:String){
        getPositionsResumeUseCase(text).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _suggestPosition.value = SuggestPositionState(suggests = result.data)
                }
                is Resource.Error -> {
                    _suggestPosition.value = SuggestPositionState(error = result.message ?: ConstantsError.SUGGEST_POSITION_ERROR)
                }
                is Resource.Loading -> {
                    _suggestPosition.value = SuggestPositionState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}