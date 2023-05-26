package com.example.find_work_it.data.remote.dto.resumes.validation_resume

import com.google.gson.annotations.SerializedName

data class ValidationSkillSet(
    @SerializedName("category") val category: ValidationRule,
    @SerializedName("skills") val skills: ValidationRule
)