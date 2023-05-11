package com.example.find_work_it.domain.model

import android.icu.text.CaseMap.Title
import com.example.find_work_it.data.remote.dto.resumes.models.Photo
import com.example.find_work_it.data.remote.dto.resumes.models.Status

data class Resume(
    val id: String,
    val title: String? = null,
    val photo: Photo? = null,
    val status: Status? = null,
    val finished: Boolean = false,
    val updatedAt: String? = null
)
