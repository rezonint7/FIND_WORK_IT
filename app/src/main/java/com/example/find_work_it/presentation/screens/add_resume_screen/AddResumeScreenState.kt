package com.example.find_work_it.presentation.screens.add_resume_screen

import com.example.find_work_it.common.Resource
import com.example.find_work_it.data.remote.dto.dictionary_areas.AreaX
import com.example.find_work_it.data.remote.dto.dictionary_areas.AreasDTO
import com.example.find_work_it.data.remote.dto.dictionary_professional_roles.ProfessionalRolesDTO
import com.example.find_work_it.data.remote.dto.dictionary_professional_roles.Role
import com.example.find_work_it.data.remote.dto.resumes.status_resume.ResumeStatusDTO
import com.example.find_work_it.data.remote.dto.resumes.validation_resume.ValidationResumeFields
import com.example.find_work_it.data.remote.dto.suggest.models.SuggestPositionResumeDTO
import com.example.find_work_it.domain.model.Dictionaries
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.domain.model.User

data class AddResumeScreenState(
    val isLoading: Boolean = false,
    val resume: ResumeDetail? = null,
    val error: String = ""
)

data class EditResumeState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String = ""
)

data class GetStatusResumeState(
    val success: ResumeStatusDTO? = null,
    val error: String = ""
)

data class PublishResumeState(
    val success: Boolean = false,
    val error: String = ""
)
data class UserInfoState(
    val user: User? = null,
    val error: String = ""
)

data class DictionariesState(
    val dictionaries: Dictionaries? = null,
    val error: String = ""
)

data class ProfessionalRolesState(
    val profRoles: List<Role>? = null,
    val error: String = ""
)

data class AreasDictionaryState(
    val areas: List<AreaX>? = null,
    val error: String = ""
)

data class ConditionsDictionaryState(
    val conditions: ValidationResumeFields? = null,
    val error: String = ""
)

data class SuggestPositionState(
    val isLoading: Boolean = false,
    val suggests: SuggestPositionResumeDTO? = null,
    val error: String = ""
)
