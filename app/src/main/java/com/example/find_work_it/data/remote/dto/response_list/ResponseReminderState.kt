package com.example.find_work_it.data.remote.dto.response_list


import com.google.gson.annotations.SerializedName

data class ResponseReminderState(
    @SerializedName("allowed")
    val allowed: Boolean?
)