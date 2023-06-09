package com.example.find_work_it.data.remote.dto.resumes.resume_detail


import com.example.find_work_it.data.remote.dto.resumes.models.Level
import com.example.find_work_it.data.remote.dto.resumes.models.Primary
import com.google.gson.annotations.SerializedName

data class Education(
    @SerializedName("additional")
    val additional: List<Additional?>? = null,
    @SerializedName("attestation")
    val attestation: List<Attestation?>? = null,
    @SerializedName("elementary")
    val elementary: List<Elementary?>? = null,
    @SerializedName("level")
    val level: Level? = null,
    @SerializedName("primary")
    val primary: List<Primary?>? = null
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