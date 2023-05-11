package com.example.find_work_it.data.remote.dto.resumes.models


import com.google.gson.annotations.SerializedName

data class Salary(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("currency")
    val currency: String?
)