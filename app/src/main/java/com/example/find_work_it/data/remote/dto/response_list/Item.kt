package com.example.find_work_it.data.remote.dto.response_list


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("chat_id")
    val chatId: Long?,
    @SerializedName("chat_states")
    val chatStates: ChatStates?,
    @SerializedName("counters")
    val counters: Counters?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("decline_allowed")
    val declineAllowed: Boolean?,
    @SerializedName("has_new_messages")
    val hasNewMessages: Boolean?,
    @SerializedName("has_updates")
    val hasUpdates: Boolean?,
    @SerializedName("hidden")
    val hidden: Boolean?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("messages_url")
    val messagesUrl: String?,
    @SerializedName("messaging_status")
    val messagingStatus: String?,
    @SerializedName("read")
    val read: Boolean?,
    @SerializedName("resume")
    val resume: Resume?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("state")
    val state: State?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("vacancy")
    val vacancy: Vacancy?,
    @SerializedName("viewed_by_opponent")
    val viewedByOpponent: Boolean?
)