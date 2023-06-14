package com.example.find_work_it.data.remote.dto.response_list


import com.google.gson.annotations.SerializedName

data class Counters(
    @SerializedName("messages")
    val messages: Int?,
    @SerializedName("unread_messages")
    val unreadMessages: Int?
)