package com.example.find_work_it.data.remote.dto.user


import com.example.find_work_it.data.remote.dto.user.models.*
import com.example.find_work_it.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("auth_type")
    val authType: String?,
    @SerializedName("counters")
    val counters: Counters,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_admin")
    val isAdmin: Boolean,
    @SerializedName("is_applicant")
    val isApplicant: Boolean,
    @SerializedName("is_application")
    val isApplication: Boolean,
    @SerializedName("is_employer")
    val isEmployer: Boolean,
    @SerializedName("is_in_search")
    val isInSearch: Boolean,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("middle_name")
    val middleName: String?,
    @SerializedName("negotiations_url")
    val negotiationsUrl: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("profile_videos")
    val profileVideos: ProfileVideos?,
    @SerializedName("resumes_url")
    val resumesUrl: String,
    @SerializedName("user_statuses")
    val userStatuses: UserStatuses?
)

fun UserDTO.toUser(): User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        middleName = middleName,
        phone = phone,
        email = email,
        isInSearch = isInSearch,
        profileVideos = profileVideos
    )
}

