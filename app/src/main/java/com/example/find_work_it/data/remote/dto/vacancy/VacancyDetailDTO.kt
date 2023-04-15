package com.example.find_work_it.data.remote.dto.vacancy


import android.os.Build
import androidx.annotation.RequiresApi
import com.example.find_work_it.data.remote.dto.vacancy.models.*
import com.example.find_work_it.domain.model.VacancyDetail
import com.google.gson.annotations.SerializedName

data class VacancyDetailDTO(
    @SerializedName("accept_handicapped")
    val acceptHandicapped: Boolean?,
    @SerializedName("accept_incomplete_resumes")
    val acceptIncompleteResumes: Boolean?,
    @SerializedName("accept_kids")
    val acceptKids: Boolean?,
    @SerializedName("accept_temporary")
    val acceptTemporary: Boolean?,
    @SerializedName("address")
    val address: Address?,
    @SerializedName("allow_messages")
    val allowMessages: Boolean?,
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    @SerializedName("apply_alternate_url")
    val applyAlternateUrl: String?,
    @SerializedName("archived")
    val archived: Boolean?,
    @SerializedName("area")
    val area: Area?,
    @SerializedName("billing_type")
    val billingType: BillingType?,
    @SerializedName("branded_description")
    val brandedDescription: Any?,
    @SerializedName("code")
    val code: Any?,
    @SerializedName("contacts")
    val contacts: Contacts?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("department")
    val department: Department?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("driver_license_types")
    val driverLicenseTypes: List<Any?>?,
    @SerializedName("employer")
    val employer: Employer?,
    @SerializedName("employment")
    val employment: Employment?,
    @SerializedName("experience")
    val experience: Experience?,
    @SerializedName("has_test")
    val hasTest: Boolean?,
    @SerializedName("hidden")
    val hidden: Boolean?,
    @SerializedName("id")
    val id: String,
    @SerializedName("initial_created_at")
    val initialCreatedAt: String?,
    @SerializedName("insider_interview")
    val insiderInterview: InsiderInterview?,
    @SerializedName("key_skills")
    val keySkills: List<KeySkill?>?,
    @SerializedName("languages")
    val languages: List<Any?>?,
    @SerializedName("name")
    val name: String,
    @SerializedName("negotiations_url")
    val negotiationsUrl: Any?,
    @SerializedName("premium")
    val premium: Boolean?,
    @SerializedName("professional_roles")
    val professionalRoles: List<ProfessionalRole?>?,
    @SerializedName("published_at")
    val publishedAt: String?,
    @SerializedName("quick_responses_allowed")
    val quickResponsesAllowed: Boolean?,
    @SerializedName("response_letter_required")
    val responseLetterRequired: Boolean?,
    @SerializedName("salary")
    val salary: Salary?,
    @SerializedName("schedule")
    val schedule: Schedule?,
    @SerializedName("specializations")
    val specializations: List<Any?>?,
    @SerializedName("suitable_resumes_url")
    val suitableResumesUrl: Any?,
    @SerializedName("type")
    val type: Type?,
    @SerializedName("vacancy_constructor_template")
    val vacancyConstructorTemplate: Any?,
    @SerializedName("working_days")
    val workingDays: List<WorkingDay?>?,
    @SerializedName("working_time_intervals")
    val workingTimeIntervals: List<WorkingTimeInterval?>?,
    @SerializedName("working_time_modes")
    val workingTimeModes: List<WorkingTimeMode?>?
)

fun VacancyDetailDTO.toVacancyDetail() : VacancyDetail {
    return VacancyDetail(
        idVacancy = id,
        nameVacancy = name,
        employer = employer,
        salary =  salary,
        address = address,
        publishDate = dateToStringFormat(publishedAt),
        schedule = schedule?.name,
        contacts = contacts
    )
}
