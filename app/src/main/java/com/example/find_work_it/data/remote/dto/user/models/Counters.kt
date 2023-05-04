package com.example.find_work_it.data.remote.dto.user.models


import com.google.gson.annotations.SerializedName

data class Counters(
    @SerializedName("new_resume_views")
    val newResumeViews: Int?,
    @SerializedName("resumes_count")
    val resumesCount: Int?,
    @SerializedName("unread_negotiations")
    val unreadNegotiations: Int?
)