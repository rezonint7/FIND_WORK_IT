package com.example.find_work_it.data.remote.dto.resumes.validation_resume

import com.google.gson.annotations.SerializedName

data class ValidationRelocation(
    @SerializedName("city") val city: ValidationRule,
    @SerializedName("country") val country: ValidationRule
)