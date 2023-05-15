package com.example.find_work_it.presentation.screens.add_resume_screen

import com.example.find_work_it.domain.model.Dictionaries
import com.example.find_work_it.domain.model.ResumeDetail

data class AddResumeScreenState(
    val isLoading: Boolean = false,
    val resume: ResumeDetail? = null,
    val error: String = ""
)

data class DictionariesState(
    val dictionaries: Dictionaries? = null,
    val error: String = ""
)
