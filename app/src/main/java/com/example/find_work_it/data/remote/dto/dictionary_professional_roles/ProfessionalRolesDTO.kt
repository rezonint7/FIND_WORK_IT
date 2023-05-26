package com.example.find_work_it.data.remote.dto.dictionary_professional_roles


import com.google.gson.annotations.SerializedName

data class ProfessionalRolesDTO(
    @SerializedName("categories")
    val categories: List<Category>
)