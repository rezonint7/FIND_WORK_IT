package com.example.find_work_it.data.remote.dto.resumes.validation_resume

import com.google.gson.annotations.SerializedName

data class AccessValidationRule(
    @SerializedName("fields") val fields: ValidationRule,
    @SerializedName("profile") val profile: ValidationRule
)
