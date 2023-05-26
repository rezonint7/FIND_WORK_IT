package com.example.find_work_it.data.remote.dto.resumes.validation_resume

import com.google.gson.annotations.SerializedName

data class ValidationLanguage(
    @SerializedName("language") val language: ValidationRule,
    @SerializedName("level") val level: ValidationRule
)