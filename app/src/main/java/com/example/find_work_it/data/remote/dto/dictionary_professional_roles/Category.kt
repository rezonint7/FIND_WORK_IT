package com.example.find_work_it.data.remote.dto.dictionary_professional_roles


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("roles")
    val roles: List<Role>
)