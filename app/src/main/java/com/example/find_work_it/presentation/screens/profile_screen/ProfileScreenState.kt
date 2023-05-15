package com.example.find_work_it.presentation.screens.profile_screen

import android.os.Parcelable
import com.example.find_work_it.domain.model.Resume
import com.example.find_work_it.domain.model.User

data class ProfileScreenState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)
data class GetResumesState(
    val resumes: MutableList<Resume> = mutableListOf(),
    val error: String = ""
)
data class EditInfoProfileScreenState(
    val success: Boolean = false,
    val error: String = ""
)

data class ValidateFieldsResult(
    val name: UserValidationState,
    val surname: UserValidationState,
    val patronymic: UserValidationState? = null
)
data class UserValidationState(val isError: Boolean, val errorMessage: String = "")

