package com.example.find_work_it.data.remote.dto.resumes.validation_resume

import com.google.gson.annotations.SerializedName

data class ValidationContact(
    @SerializedName("home") val home: ValidationRule?,
    @SerializedName("mobile") val mobile: ValidationRule,
    @SerializedName("email") val email: ValidationRule,
    @SerializedName("skype") val skype: ValidationRule?,
    @SerializedName("other") val other: ValidationRule?
)