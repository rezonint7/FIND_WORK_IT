package com.example.find_work_it.data.remote.dto.resumes.validation_resume

import com.google.gson.annotations.SerializedName

data class ValidationSite(
    @SerializedName("url") val url: ValidationRule,
    @SerializedName("name") val name: ValidationRule
)