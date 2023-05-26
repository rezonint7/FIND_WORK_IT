package com.example.find_work_it.presentation.screens.profile_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.resumes.models.Area
import com.example.find_work_it.data.remote.dto.resumes.models.Contact
import com.example.find_work_it.data.remote.dto.resumes.models.Gender
import com.example.find_work_it.data.remote.dto.resumes.models.Level
import com.example.find_work_it.data.remote.dto.resumes.models.Type
import com.example.find_work_it.data.remote.dto.resumes.models.Value
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.BusinessTripReadiness
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Education
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Employment
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Language
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.ProfessionalRole
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Schedule
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.domain.model.User
import com.example.find_work_it.domain.use_case.resumes.CreateNewResumeUseCase
import com.example.find_work_it.domain.use_case.resumes.GetUserResumesUseCase
import com.example.find_work_it.domain.use_case.user.GetUserInfoUseCase
import com.example.find_work_it.domain.use_case.user.PutUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserResumesUseCase: GetUserResumesUseCase,
    private val createNewResumeUseCase: CreateNewResumeUseCase,
    private val putUserInfoUseCase: PutUserInfoUseCase,) : ViewModel() {
    private val _state: MutableLiveData<ProfileScreenState> = MutableLiveData(ProfileScreenState())
    private val _editInfoState = mutableStateOf<EditInfoProfileScreenState>(EditInfoProfileScreenState())
    private val _createNewResume = mutableStateOf<CreateNewResumeState>(CreateNewResumeState())
    private val _getResumes = MutableLiveData<GetResumesState>()

    val state: MutableLiveData<ProfileScreenState> = _state
    val resumes: LiveData<GetResumesState> = _getResumes
    val editInfoState: State<EditInfoProfileScreenState> = _editInfoState
    val createNewResumeState: State<CreateNewResumeState> = _createNewResume
    init {
        getUserInfo()
        getResumesForUser()
    }

    private fun getUserInfo(){
        getUserInfoUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = ProfileScreenState(user = result.data)
                    Log.d("STATE", _state.value!!.user.toString())
                }
                is Resource.Error -> {
                    _state.value = ProfileScreenState(error = result.message ?: ConstantsError.ERROR_OCCURRED)
                }
                is Resource.Loading -> {
                    _state.value = ProfileScreenState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

    private fun getResumesForUser(){
        getUserResumesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _getResumes.postValue(GetResumesState(resumes = (result.data ?: emptyList()).toMutableList()))
                }
                is Resource.Error -> {
                    _getResumes.postValue(GetResumesState(error = result.message ?: ConstantsError.ERROR_OCCURRED))
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun editUserInfo(body: HashMap<String, String?>){
        putUserInfoUseCase(body).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _editInfoState.value = EditInfoProfileScreenState(success = result.data ?: false)
                    getUserInfo()
                    Log.d("TOKENS", _editInfoState.value.success.toString())
                }
                is Resource.Error -> {
                    _editInfoState.value = EditInfoProfileScreenState(error = result.message ?: ConstantsError.PUT_USER_ERROR_OCCURRED)
                    Log.d("TOKENS", _editInfoState.value.error.toString())
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun createNewResume(){
        val newResume = ResumeDetail(
            lastName = _state.value?.user?.lastName.toString(),
            firstName = _state.value?.user?.firstName.toString(),
            gender = Gender("male", "Мужской"),
            area = Area("1", "Москва"),
            contact = listOf(
                Contact(preferred = false, type = Type("email", "Эл. почта"), verified = false, value = _state.value?.user?.email.toString()),
                Contact(preferred = true, type = Type("cell", "Мобильный телефон"), verified = false, value = Value(formatted = "+79521463956"))
            ),
            education = Education(
                primary = listOf(),
                level = Level("higher", "Высшее")
            ),
            businessTripReadiness = BusinessTripReadiness("never", "не готов к командировкам"),
            professionalRoles = listOf(ProfessionalRole("96", "Программист, разработчик")),
            employments = listOf(Employment("full", "Полная занятость")),
            schedules = listOf(Schedule("fullDay", "Полный день")),
            language = listOf(Language(
                id = "rus",
                name = "Русский",
                level = Level("l1", "Родной")
            )),
        )
        createNewResumeUseCase(newResume).onEach { result ->
            when(result){
                is Resource.Success -> {
                    Log.d("RESUME1", "Создано")
                    _createNewResume.value = CreateNewResumeState(success = result.data == true)
                    getResumesForUser()
                }
                is Resource.Loading -> {
                    _createNewResume.value = CreateNewResumeState(isLoading = true)
                }
                is Resource.Error -> {
                    _createNewResume.value = CreateNewResumeState(error = result.message ?: ConstantsError.RESUME_CREATE_ERROR_OCCURRED)

                }
            }
        }.launchIn(viewModelScope)
    }

    fun validateUserInfoFields(user: User): ValidateFieldsResult{
        val firstNameIsValidate = user.firstName.isNotBlank() && user.firstName.matches(Regex("^[а-яА-ЯёЁ]+$"))
        val lastNameIsValidate = user.lastName.isNotBlank() && user.firstName.matches(Regex("^[а-яА-ЯёЁ]+$"))
        val middleNameIsValidate = user.middleName?.matches(Regex("^[а-яА-ЯёЁ]+$"))
        return ValidateFieldsResult(
            UserValidationState(firstNameIsValidate, if (firstNameIsValidate) "" else ConstantsError.USER_FIRSTNAME_ERROR_VALIDATE),
            UserValidationState(lastNameIsValidate, if (lastNameIsValidate) "" else ConstantsError.USER_LASTNAME_ERROR_VALIDATE),
            if(user.middleName.isNullOrEmpty()) null else UserValidationState(middleNameIsValidate!!, if (middleNameIsValidate) "" else ConstantsError.USER_MIDDLENAME_ERROR_VALIDATE),
        )
    }
    fun validateUserFields(value: String): Boolean{
        return value.isNotBlank() && value.matches(Regex("^[а-яА-ЯёЁ]+$"))
    }
    fun validateMiddleNameUserField(value: String?): Boolean{
        return if(value.isNullOrEmpty()) true else value.matches(Regex("^[а-яА-ЯёЁ]+$"))
    }
}