package com.example.find_work_it.data.remote.dto.dictionary_areas


import com.google.gson.annotations.SerializedName

data class AreasDTO(
    @SerializedName("areas")
    val areas: List<Area>,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("parent_id")
    val parentId: Any?
)