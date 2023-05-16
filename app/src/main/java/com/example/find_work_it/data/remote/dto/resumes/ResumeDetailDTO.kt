package com.example.find_work_it.data.remote.dto.resumes


import com.example.find_work_it.data.remote.dto.resumes.models.Area
import com.example.find_work_it.data.remote.dto.resumes.models.Certificate
import com.example.find_work_it.data.remote.dto.resumes.models.Contact
import com.example.find_work_it.data.remote.dto.resumes.models.Download
import com.example.find_work_it.data.remote.dto.resumes.models.Experience
import com.example.find_work_it.data.remote.dto.resumes.models.Gender
import com.example.find_work_it.data.remote.dto.resumes.models.HiddenField
import com.example.find_work_it.data.remote.dto.resumes.models.Photo
import com.example.find_work_it.data.remote.dto.resumes.models.Platform
import com.example.find_work_it.data.remote.dto.resumes.models.Salary
import com.example.find_work_it.data.remote.dto.resumes.models.Status
import com.example.find_work_it.data.remote.dto.resumes.models.TotalExperience
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Actions
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.BusinessTripReadiness
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Citizenship
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.DriverLicenseType
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Education
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Employment
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.JobSearchStatus
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Language
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Metro
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Portfolio
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.ProfessionalRole
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Recommendation
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Relocation
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.ResumeLocale
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Schedule
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Site
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.TravelTime
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.WorkTicket
import com.example.find_work_it.domain.model.ResumeDetail
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.exp

data class ResumeDetailDTO(
    @SerializedName("actions")
    val actions: Actions?,
    @SerializedName("age")
    val age: Int?,
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    @SerializedName("area")
    val area: Area?,
    @SerializedName("birth_date")
    val birthDate: String?,
    @SerializedName("business_trip_readiness")
    val businessTripReadiness: BusinessTripReadiness?,
    @SerializedName("certificate")
    val certificate: List<Certificate>?,
    @SerializedName("citizenship")
    val citizenship: List<Citizenship>?,
    @SerializedName("contact")
    val contact: List<Contact>?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("download")
    val download: Download?,
    @SerializedName("driver_license_types")
    val driverLicenseTypes: List<DriverLicenseType>?,
    @SerializedName("education")
    val education: Education?,
    @SerializedName("employments")
    val employments: List<Employment>?,
    @SerializedName("experience")
    val experience: List<Experience>?,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: Gender?,
    @SerializedName("has_vehicle")
    val hasVehicle: Boolean?,
    @SerializedName("hidden_fields")
    val hiddenFields: List<HiddenField>?,
    @SerializedName("id")
    val id: String,
    @SerializedName("job_search_status")
    val jobSearchStatus: JobSearchStatus?,
    @SerializedName("language")
    val language: List<Language>?,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("marked")
    val marked: Boolean?,
    @SerializedName("metro")
    val metro: Metro?,
    @SerializedName("middle_name")
    val middleName: String?,
    @SerializedName("photo")
    val photo: Photo?,
    @SerializedName("platform")
    val platform: Platform?,
    @SerializedName("portfolio")
    val portfolio: List<Portfolio>?,
    @SerializedName("professional_roles")
    val professionalRoles: List<ProfessionalRole>?,
    @SerializedName("recommendation")
    val recommendation: List<Recommendation>?,
    @SerializedName("relocation")
    val relocation: Relocation?,
    @SerializedName("resume_locale")
    val resumeLocale: ResumeLocale?,
    @SerializedName("salary")
    val salary: Salary?,
    @SerializedName("schedules")
    val schedules: List<Schedule>?,
    @SerializedName("site")
    val site: List<Site>?,
    @SerializedName("skill_set")
    val skillSet: List<String>?,
    @SerializedName("skills")
    val skills: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("total_experience")
    val totalExperience: TotalExperience?,
    @SerializedName("travel_time")
    val travelTime: TravelTime?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("work_ticket")
    val workTicket: List<WorkTicket>?,
    @SerializedName("status")
    val status: Status,
    @SerializedName("finished")
    val finished: Boolean
)

fun ResumeDetailDTO.toResumeDetail(): ResumeDetail{
    return ResumeDetail(
        resumeId = id,
        lastName = lastName,
        firstName = firstName,
        middleName = middleName,
        birthDate = convertFromDate(birthDate),
        age = age,
        gender = gender,
        area = area,
        contact = contact,
        title = title,
        photo = photo,
        salary = salary,
        education = education,
        experience = experience,
        status = status,
        finished = finished,
        skills = skills,
        skills_set = skillSet,
        certificate = certificate
    )
}

fun convertFromDate(date: String?): String?{
    if(date.isNullOrBlank()) return null
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val zonedDateTime = LocalDate.parse(date, formatter)

    val dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale("ru", "RU"))
    return dtf.format(zonedDateTime)
}
fun convertToDate(date: String?): String?{
    if(date.isNullOrBlank()) return null
    val inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val dateTime = ZonedDateTime.parse(date, inputFormatter)

    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale("ru", "RU"))

    return outputFormatter.format(dateTime)
}