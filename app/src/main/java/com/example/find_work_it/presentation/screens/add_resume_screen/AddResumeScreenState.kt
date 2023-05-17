package com.example.find_work_it.presentation.screens.add_resume_screen

import com.example.find_work_it.data.remote.dto.dictionary_areas.AreasDTO
import com.example.find_work_it.data.remote.dto.suggest.models.SuggestPositionResumeDTO
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

data class AreasDictionaryState(
    val areas: AreasDTO? = null,
    val error: String = ""
)

data class SuggestPositionState(
    val isLoading: Boolean = false,
    val suggests: SuggestPositionResumeDTO? = null,
    val error: String = ""
)
