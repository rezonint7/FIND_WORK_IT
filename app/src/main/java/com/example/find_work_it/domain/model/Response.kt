package com.example.find_work_it.domain.model

import com.example.find_work_it.data.remote.dto.response_list.Resume
import com.example.find_work_it.data.remote.dto.response_list.State
import com.example.find_work_it.data.remote.dto.response_list.Vacancy

data class Response(
    val id: String?,
    val status: State?,
    val resume: Resume?,
    val vacancy: Vacancy?,
    val createdAt: String?,
    val updatedAt: String?,
)
