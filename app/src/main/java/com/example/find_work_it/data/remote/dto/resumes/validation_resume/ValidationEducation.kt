package com.example.find_work_it.data.remote.dto.resumes.validation_resume

import com.google.gson.annotations.SerializedName

data class ValidationEducation(
    @SerializedName("level") val level: ValidationRule,
    @SerializedName("university") val university: ValidationRule?,
    @SerializedName("faculty") val faculty: ValidationRule?,
    @SerializedName("department") val department: ValidationRule?,
    @SerializedName("speciality") val speciality: ValidationRule?,
    @SerializedName("diploma") val diploma: ValidationRule?,
    @SerializedName("start_date") val startDate: ValidationRule?,
    @SerializedName("end_date") val endDate: ValidationRule?,
    @SerializedName("current") val current: ValidationRule
)