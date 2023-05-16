package com.example.find_work_it.data.remote.dto.suggest.models


import com.google.gson.annotations.SerializedName

data class SuggestPositionResumeDTO(
    @SerializedName("items")
    val items: List<Item>
)