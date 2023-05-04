package com.example.find_work_it.data.remote.dto.user.models


import com.google.gson.annotations.SerializedName

data class UserStatuses(
    @SerializedName("job_search_status")
    val jobSearchStatus: JobSearchStatus?,
    @SerializedName("when_can_start_work_status")
    val whenCanStartWorkStatus: WhenCanStartWorkStatus?
)