package com.example.find_work_it.presentation.screens.profile_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.common.Resource
import com.example.find_work_it.domain.model.User
import com.example.find_work_it.domain.use_case.user.GetUserInfoUseCase
import com.example.find_work_it.domain.use_case.user.PutUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val putUserInfoUseCase: PutUserInfoUseCase) : ViewModel() {
    private val _state = mutableStateOf<ProfileScreenState>(ProfileScreenState())
    private val _editInfoState = mutableStateOf<EditInfoProfileScreenState>(
        EditInfoProfileScreenState()
    )
    val state: State<ProfileScreenState> = _state
    val editInfoState: State<EditInfoProfileScreenState> = _editInfoState
    init {
        getUserInfo()
    }

    private fun getUserInfo(){
        getUserInfoUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = ProfileScreenState(user = result.data)
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

    fun editUserInfo(firstName: String, lastName: String, middleName: String){
        putUserInfoUseCase(firstName, lastName, middleName).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _editInfoState.value = EditInfoProfileScreenState(success = result.data ?: false)
                }
                is Resource.Error -> {
                    _editInfoState.value = EditInfoProfileScreenState(error = result.message ?: ConstantsError.PUT_USER_ERROR_OCCURRED)
                }
                else -> {}
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