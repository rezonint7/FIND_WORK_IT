package com.example.find_work_it.data.remote.dto.resumes.status_resume


import com.google.gson.annotations.SerializedName

data class ResumeStatusDTO(
    @SerializedName("blocked")
    val blocked: Boolean,
    @SerializedName("can_publish_or_update")
    val canPublishOrUpdate: Boolean,
    @SerializedName("finished")
    val finished: Boolean,
    @SerializedName("moderation_note")
    val moderationNote: List<ModerationNote>,
    @SerializedName("progress")
    val progress: Progress,
    @SerializedName("publish_url")
    val publishUrl: String,
    @SerializedName("status")
    val status: Status
)