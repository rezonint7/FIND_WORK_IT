package com.example.find_work_it.data.remote.dto.user.models


import com.google.gson.annotations.SerializedName

data class WhenCanStartWorkStatus(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)