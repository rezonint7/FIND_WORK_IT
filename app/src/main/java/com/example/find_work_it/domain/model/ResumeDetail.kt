package com.example.find_work_it.domain.model

import com.example.find_work_it.data.remote.dto.resumes.models.Area
import com.example.find_work_it.data.remote.dto.resumes.models.Certificate
import com.example.find_work_it.data.remote.dto.resumes.models.Contact
import com.example.find_work_it.data.remote.dto.resumes.models.Experience
import com.example.find_work_it.data.remote.dto.resumes.models.Gender
import com.example.find_work_it.data.remote.dto.resumes.models.Photo
import com.example.find_work_it.data.remote.dto.resumes.models.Salary
import com.example.find_work_it.data.remote.dto.resumes.models.Status
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.BusinessTripReadiness
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Citizenship
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Education
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Employment
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Language
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.ProfessionalRole
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Schedule
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.WorkTicket
import com.google.gson.annotations.SerializedName

data class ResumeDetail(
    val resumeId: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("middle_name")
    val middleName: String? = null,
    @SerializedName("birth_date")
    val birthDate: String? = null,
    val age: Int? = null,
    val gender: Gender?,
    val area: Area?,
    val contact: List<Contact>?,
    val title: String? = null,
    val photo: Photo? = null,
    val salary: Salary? = null,
    val education: Education?,
    @SerializedName("business_trip_readiness")
    val businessTripReadiness: BusinessTripReadiness?,
    @SerializedName("professional_roles")
    val professionalRoles: List<ProfessionalRole>?,
    val employments: List<Employment>?,
    val schedules: List<Schedule>?,
    val language: List<Language>?,
    val experience: List<Experience>? = null,
    val status: Status? = null,
    val finished: Boolean = false,
    val skills: String? = null,
    @SerializedName("skills_set")
    val skills_set: List<String>? = null,
    val certificate: List<Certificate>? = null,
    val citizenship: List<Citizenship>? = null,
    @SerializedName("work_ticket")
    val workTicket: List<WorkTicket>? = null
)
