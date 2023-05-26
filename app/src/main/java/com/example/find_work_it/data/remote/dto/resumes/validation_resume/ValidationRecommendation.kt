package com.example.find_work_it.data.remote.dto.resumes.validation_resume

import com.google.gson.annotations.SerializedName

data class ValidationRecommendation(
    @SerializedName("text") val text: ValidationRule,
    @SerializedName("contact") val contact: ValidationRule?,
    @SerializedName("name") val name: ValidationRule?
)