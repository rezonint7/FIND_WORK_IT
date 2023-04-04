package com.example.find_work_it.data.remote.dto.vacancy


import com.google.gson.annotations.SerializedName

data class Salary(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("from")
    val from: Int,
    @SerializedName("gross")
    val gross: Boolean,
    @SerializedName("to")
    val to: Any?
)