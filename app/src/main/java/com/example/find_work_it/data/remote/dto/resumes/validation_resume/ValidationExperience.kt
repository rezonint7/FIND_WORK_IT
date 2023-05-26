package com.example.find_work_it.data.remote.dto.resumes.validation_resume

import com.google.gson.annotations.SerializedName

data class ValidationExperience(
    @SerializedName("position") val position: ValidationRule,
    @SerializedName("company") val company: ValidationRule?,
    @SerializedName("description") val description: ValidationRule?,
    @SerializedName("start_date") val startDate: ValidationRule?,
    @SerializedName("end_date") val endDate: ValidationRule?,
    @SerializedName("current") val current: ValidationRule
)