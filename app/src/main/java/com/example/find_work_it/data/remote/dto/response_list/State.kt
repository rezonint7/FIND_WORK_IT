package com.example.find_work_it.data.remote.dto.response_list


import com.google.gson.annotations.SerializedName

data class State(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)