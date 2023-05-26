package com.example.find_work_it.presentation.screens.add_resume_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.dictionary_areas.AreaX
import com.example.find_work_it.domain.use_case.dictionaries.GetAreasDictionaryUseCase
import com.example.find_work_it.domain.use_case.dictionaries.GetDictionariesUseCase
import com.example.find_work_it.domain.use_case.dictionaries.GetProfessionalRolesUseCase
import com.example.find_work_it.domain.use_case.resumes.GetConditionsResumeUseCase
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
    private val getProfessionalRolesUseCase: GetProfessionalRolesUseCase,
    private val getConditionsResumeUseCase: GetConditionsResumeUseCase,
    private val getAreasDictionaryUseCase: GetAreasDictionaryUseCase,
    savedStateHandle: SavedStateHandle): ViewModel() {
    private val _state = mutableStateOf<AddResumeScreenState>(AddResumeScreenState())
    private val _dictionaries = mutableStateOf<DictionariesState>(DictionariesState())
    private val _areasDictionary = mutableStateOf<AreasDictionaryState>(AreasDictionaryState())
    private val _professionalRoles = mutableStateOf<ProfessionalRolesState>(ProfessionalRolesState())
    private val _conditionsResume = mutableStateOf<ConditionsDictionaryState>(ConditionsDictionaryState())
    private val _suggestPosition: MutableLiveData<SuggestPositionState> = MutableLiveData(SuggestPositionState())

    val state: State<AddResumeScreenState> = _state
    val dictionariesState: State<DictionariesState> = _dictionaries
    val areasDictionaryState: State<AreasDictionaryState> = _areasDictionary
    val professionalRoles: State<ProfessionalRolesState> = _professionalRoles
    val suggestPositions: MutableLiveData<SuggestPositionState> = _suggestPosition


    init {
        savedStateHandle.get<String>(Constants.PARAM_RESUME_ID)?.let{ resumeId ->
            getResume(resumeId)
        }
        getDictionaries()
        getAreasDictionary()
        getConditionsResume()
        getProfessionalRoles()
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
                    _state.value = AddResumeScreenState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
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
                    _dictionaries.value = DictionariesState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                else -> {}
            }

        }.launchIn(viewModelScope)
    }

    private fun getConditionsResume(){
        getConditionsResumeUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _conditionsResume.value = ConditionsDictionaryState(conditions = result.data)
                }
                is Resource.Error -> {
                    _conditionsResume.value = ConditionsDictionaryState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun getAreasDictionary(){
        getAreasDictionaryUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    val areas = result.data!!.areas.flatMap { it.areas }.toMutableList()
                    areas.add(AreaX(areas = listOf(), id= "1", parentId = "113", name = "Москва"))
                    areas.add(AreaX(areas = listOf(), id= "2", parentId = "113", name = "Санкт-Петербург"))
                    _areasDictionary.value = AreasDictionaryState(areas)
                }
                is Resource.Error -> {
                    _areasDictionary.value = AreasDictionaryState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun getProfessionalRoles(){
        getProfessionalRolesUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    val roles = result.data!!.categories.filter { it.id == "11" }.flatMap{ it.roles }
                    _professionalRoles.value = ProfessionalRolesState(roles)
                }
                is Resource.Error -> {
                    _professionalRoles.value = ProfessionalRolesState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    // пока не используется
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

    fun validateResumeFields(resumeState: TextFieldState): TextFieldStateError{
        val errors = mutableMapOf<String, String>()

        val regexLastName = resumeState.lastName.matches(Regex(_conditionsResume.value.conditions?.lastName?.regexp.toString()))
        val regexFirstName = resumeState.firstName.matches(Regex(_conditionsResume.value.conditions?.firstName?.regexp.toString()))
        val regexMiddleName = resumeState.middleName.matches(Regex(_conditionsResume.value.conditions?.middleName?.regexp.toString()))
        val regexPhone = resumeState.phone.matches(Regex("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}\$"))
        errors["last_name"] = when{
            resumeState.lastName.isBlank() -> "Поле фамилии не может быть пустым"
            !regexLastName -> "Поле фамилии может содержать только буквы"
            resumeState.lastName.isNotBlank() && regexLastName -> ""
            else -> "Ошибка в заполнении поля"
        }
        errors["first_name"] = when{
            resumeState.firstName.isBlank() -> "Поле имени не может быть пустым"
            !regexFirstName -> "Поле имени может содержать только буквы"
            resumeState.firstName.isNotBlank() && regexFirstName -> ""
            else -> "Ошибка в заполнении поля"
        }
        errors["middle_name"] = when{
            !regexMiddleName -> "Поле отчества может содержать только буквы"
            resumeState.middleName.isNotBlank() && regexMiddleName -> ""
            else -> "Ошибка в заполнении поля"
        }
        errors["phone"] = when{
            !regexPhone -> "Поле номера содержит ошибку"
            resumeState.phone.isNotBlank() && regexPhone -> ""
            else -> "Ошибка в заполнении поля"
        }
        Log.d("123", errors["phone"].toString())
        return TextFieldStateError(
            lastNameError = errors["last_name"].toString(),
            firstNameError = errors["first_name"].toString(),
            middleNameError = errors["middle_name"].toString(),
            phoneError = errors["phone"].toString(),
        )
    }
}