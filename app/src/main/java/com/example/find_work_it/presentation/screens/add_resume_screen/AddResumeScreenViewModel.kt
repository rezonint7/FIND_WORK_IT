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
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.domain.use_case.dictionaries.GetAreasDictionaryUseCase
import com.example.find_work_it.domain.use_case.dictionaries.GetDictionariesUseCase
import com.example.find_work_it.domain.use_case.dictionaries.GetProfessionalRolesUseCase
import com.example.find_work_it.domain.use_case.resumes.EditResumeUseCase
import com.example.find_work_it.domain.use_case.resumes.GetConditionsResumeUseCase
import com.example.find_work_it.domain.use_case.resumes.GetStatusResumeUseCase
import com.example.find_work_it.domain.use_case.resumes.GetUserResumeDetailUseCase
import com.example.find_work_it.domain.use_case.resumes.PublishResumeUseCase
import com.example.find_work_it.domain.use_case.suggest.GetPositionsResumeUseCase
import com.example.find_work_it.domain.use_case.user.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddResumeScreenViewModel @Inject constructor(
    private val getUserResumeDetailUseCase: GetUserResumeDetailUseCase,
    private val editResumeUseCase: EditResumeUseCase,
    private val publishResumeUseCase: PublishResumeUseCase,
    private val getStatusResumeUseCase: GetStatusResumeUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
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

    private val _editResumeState = mutableStateOf<EditResumeState>(EditResumeState())
    private val _publishResumeState = mutableStateOf<PublishResumeState>(PublishResumeState())
    private val _getResumeStatusState = MutableLiveData<GetStatusResumeState>(GetStatusResumeState())
    private val _getUserInfo = mutableStateOf<UserInfoState>(UserInfoState())

    val state: State<AddResumeScreenState> = _state
    val user: State<UserInfoState> = _getUserInfo
    val dictionariesState: State<DictionariesState> = _dictionaries
    val areasDictionaryState: State<AreasDictionaryState> = _areasDictionary
    val professionalRoles: State<ProfessionalRolesState> = _professionalRoles
    val suggestPositions: MutableLiveData<SuggestPositionState> = _suggestPosition

    val editResumeState: State<EditResumeState> = _editResumeState
    val publishResumeState: State<PublishResumeState> = _publishResumeState
    val getStatusResumeState: MutableLiveData<GetStatusResumeState> = _getResumeStatusState

    init {
        savedStateHandle.get<String>(Constants.PARAM_RESUME_ID)?.let{ resumeId ->
            getResume(resumeId)
        }
        getDictionaries()
        getAreasDictionary()
        getConditionsResume()
        getProfessionalRoles()
        getUserInfo()
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

    fun editResume(resumeId: String, resume: ResumeDetail){
        editResumeUseCase(resumeId, resume).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _editResumeState.value = EditResumeState(success = result.data == true)
                    Log.d("EDIT_RESUME", "изменено")

                }
                is Resource.Loading -> {
                    _editResumeState.value = EditResumeState(isLoading = true)
                }
                is Resource.Error -> {
                    _editResumeState.value = EditResumeState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUserInfo(){
        getUserInfoUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _getUserInfo.value = UserInfoState(user = result.data)
                }
                is Resource.Error -> {
                    _getUserInfo.value = UserInfoState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
    fun publishResume(resumeId: String){
        publishResumeUseCase(resumeId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _publishResumeState.value = PublishResumeState(success = result.data == true)
                }
                is Resource.Error -> {
                    _publishResumeState.value = PublishResumeState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun getStatusResume(resumeId: String){
        getStatusResumeUseCase(resumeId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _getResumeStatusState.value = GetStatusResumeState(success = result.data)
                    Log.d("resumeStatus", _getResumeStatusState.value?.success.toString())
                }
                is Resource.Error -> {
                    _getResumeStatusState.value = GetStatusResumeState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                else -> {}
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