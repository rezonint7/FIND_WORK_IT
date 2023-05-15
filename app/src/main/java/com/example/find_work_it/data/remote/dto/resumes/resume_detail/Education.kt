package com.example.find_work_it.data.remote.dto.resumes.resume_detail


import com.example.find_work_it.data.remote.dto.resumes.models.Primary
import com.google.gson.annotations.SerializedName

data class Education(
    @SerializedName("additional")
    val additional: List<Additional?>?,
    @SerializedName("attestation")
    val attestation: List<Attestation?>?,
    @SerializedName("elementary")
    val elementary: List<Elementary?>?,
    @SerializedName("level")
    val level: Level?,
    @SerializedName("primary")
    val primary: List<Primary?>?
)

data class Additional(
    @SerializedName("name")
    val name: String?,
    @SerializedName("organization")
    val organization: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("year")
    val year: Int?
)

data class Attestation(
    @SerializedName("name")
    val name: String?,
    @SerializedName("organization")
    val organization: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("year")
    val year: Int?
)

data class Elementary(
    @SerializedName("name")
    val name: String?,
    @SerializedName("year")
    val year: Int?
)