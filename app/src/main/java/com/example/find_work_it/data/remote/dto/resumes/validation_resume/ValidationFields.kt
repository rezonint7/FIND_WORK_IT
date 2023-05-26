package com.example.find_work_it.data.remote.dto.resumes.validation_resume

import com.google.gson.annotations.SerializedName

data class ValidationFields(
    @SerializedName("type") val type: ValidationRule,
    @SerializedName("comment") val comment: ValidationRule? = null,
    @SerializedName("value") val value: ValidationRule,
    @SerializedName("preferred") val preferred: ValidationRule
)