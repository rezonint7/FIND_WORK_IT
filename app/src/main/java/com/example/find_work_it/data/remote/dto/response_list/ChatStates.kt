package com.example.find_work_it.data.remote.dto.response_list


import com.google.gson.annotations.SerializedName

data class ChatStates(
    @SerializedName("response_reminder_state")
    val responseReminderState: ResponseReminderState?
)