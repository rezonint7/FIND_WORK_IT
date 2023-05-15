package com.example.find_work_it.data.remote.dto.dictionaries.models


import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("abbr")
    val abbr: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("default")
    val default: Boolean,
    @SerializedName("in_use")
    val inUse: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("rate")
    val rate: Double
)