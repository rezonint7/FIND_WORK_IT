package com.example.find_work_it.data.remote.dto.resumes.validation_resume

import com.google.gson.annotations.SerializedName

data class ValidationRule(
    @SerializedName("required") val required: Boolean,
    @SerializedName("regexp") val regexp: String? = null,
    @SerializedName("min_length") val minLength: Int? = null,
    @SerializedName("max_length") val maxLength: Int? = null,
    @SerializedName("min_value") val minValue: Int? = null,
    @SerializedName("max_value") val maxValue: Int? = null,
    @SerializedName("not_in") val notIn: List<String>? = null,
    @SerializedName("min_date") val minDate: String? = null,
    @SerializedName("max_date") val maxDate: String? = null,
    @SerializedName("fields") val fields: ValidationFields? = null,
    @SerializedName("min_count") val minCount: Int? = null,
    @SerializedName("max_count") val maxCount: Int? = null
)








