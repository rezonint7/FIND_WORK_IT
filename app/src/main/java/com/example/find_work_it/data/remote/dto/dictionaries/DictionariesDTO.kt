package com.example.find_work_it.data.remote.dto.dictionaries


import com.example.find_work_it.data.remote.dto.dictionaries.models.ApplicantCommentAccessType
import com.example.find_work_it.data.remote.dto.dictionaries.models.ApplicantCommentsOrder
import com.example.find_work_it.data.remote.dto.dictionaries.models.ApplicantNegotiationStatu
import com.example.find_work_it.data.remote.dto.dictionaries.models.BusinessTripReadines
import com.example.find_work_it.data.remote.dto.dictionaries.models.Currency
import com.example.find_work_it.data.remote.dto.dictionaries.models.DriverLicenseType
import com.example.find_work_it.data.remote.dto.dictionaries.models.EducationLevel
import com.example.find_work_it.data.remote.dto.dictionaries.models.EmployerActiveVacanciesOrder
import com.example.find_work_it.data.remote.dto.dictionaries.models.EmployerArchivedVacanciesOrder
import com.example.find_work_it.data.remote.dto.dictionaries.models.EmployerHiddenVacanciesOrder
import com.example.find_work_it.data.remote.dto.dictionaries.models.EmployerRelation
import com.example.find_work_it.data.remote.dto.dictionaries.models.EmployerType
import com.example.find_work_it.data.remote.dto.dictionaries.models.Experience
import com.example.find_work_it.data.remote.dto.dictionaries.models.Gender
import com.example.find_work_it.data.remote.dto.dictionaries.models.JobSearchStatusesApplicant
import com.example.find_work_it.data.remote.dto.dictionaries.models.JobSearchStatusesEmployer
import com.example.find_work_it.data.remote.dto.dictionaries.models.LanguageLevel
import com.example.find_work_it.data.remote.dto.dictionaries.models.MessagingStatu
import com.example.find_work_it.data.remote.dto.dictionaries.models.NegotiationsOrder
import com.example.find_work_it.data.remote.dto.dictionaries.models.NegotiationsParticipantType
import com.example.find_work_it.data.remote.dto.dictionaries.models.NegotiationsState
import com.example.find_work_it.data.remote.dto.dictionaries.models.PhoneCallStatu
import com.example.find_work_it.data.remote.dto.dictionaries.models.PreferredContactType
import com.example.find_work_it.data.remote.dto.dictionaries.models.RelocationType
import com.example.find_work_it.data.remote.dto.dictionaries.models.ResumeAccessType
import com.example.find_work_it.data.remote.dto.dictionaries.models.ResumeContactsSiteType
import com.example.find_work_it.data.remote.dto.dictionaries.models.ResumeHiddenField
import com.example.find_work_it.data.remote.dto.dictionaries.models.ResumeModerationNote
import com.example.find_work_it.data.remote.dto.dictionaries.models.ResumeSearchExperiencePeriod
import com.example.find_work_it.data.remote.dto.dictionaries.models.ResumeSearchField
import com.example.find_work_it.data.remote.dto.dictionaries.models.ResumeSearchLabel
import com.example.find_work_it.data.remote.dto.dictionaries.models.ResumeSearchLogic
import com.example.find_work_it.data.remote.dto.dictionaries.models.ResumeSearchOrder
import com.example.find_work_it.data.remote.dto.dictionaries.models.ResumeSearchRelocation
import com.example.find_work_it.data.remote.dto.dictionaries.models.ResumeStatus
import com.example.find_work_it.data.remote.dto.dictionaries.models.TravelTime
import com.example.find_work_it.data.remote.dto.dictionaries.models.VacancyBillingType
import com.example.find_work_it.data.remote.dto.dictionaries.models.VacancyCluster
import com.example.find_work_it.data.remote.dto.dictionaries.models.VacancyLabel
import com.example.find_work_it.data.remote.dto.dictionaries.models.VacancyNotProlongedReason
import com.example.find_work_it.data.remote.dto.dictionaries.models.VacancyRelation
import com.example.find_work_it.data.remote.dto.dictionaries.models.VacancySearchField
import com.example.find_work_it.data.remote.dto.dictionaries.models.VacancySearchOrder
import com.example.find_work_it.data.remote.dto.dictionaries.models.VacancyType
import com.example.find_work_it.data.remote.dto.dictionaries.models.WorkingDay
import com.example.find_work_it.data.remote.dto.dictionaries.models.WorkingTimeInterval
import com.example.find_work_it.data.remote.dto.dictionaries.models.WorkingTimeMode
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Employment
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Schedule
import com.example.find_work_it.domain.model.Dictionaries
import com.google.gson.annotations.SerializedName

data class DictionariesDTO(
    @SerializedName("applicant_comment_access_type")
    val applicantCommentAccessType: List<ApplicantCommentAccessType>,
    @SerializedName("applicant_comments_order")
    val applicantCommentsOrder: List<ApplicantCommentsOrder>,
    @SerializedName("applicant_negotiation_status")
    val applicantNegotiationStatus: List<ApplicantNegotiationStatu>,
    @SerializedName("business_trip_readiness")
    val businessTripReadiness: List<BusinessTripReadines>,
    @SerializedName("currency")
    val currency: List<Currency>,
    @SerializedName("driver_license_types")
    val driverLicenseTypes: List<DriverLicenseType>,
    @SerializedName("education_level")
    val educationLevel: List<EducationLevel>,
    @SerializedName("employer_active_vacancies_order")
    val employerActiveVacanciesOrder: List<EmployerActiveVacanciesOrder>,
    @SerializedName("employer_archived_vacancies_order")
    val employerArchivedVacanciesOrder: List<EmployerArchivedVacanciesOrder>,
    @SerializedName("employer_hidden_vacancies_order")
    val employerHiddenVacanciesOrder: List<EmployerHiddenVacanciesOrder>,
    @SerializedName("employer_relation")
    val employerRelation: List<EmployerRelation>,
    @SerializedName("employer_type")
    val employerType: List<EmployerType>,
    @SerializedName("employment")
    val employment: List<Employment>,
    @SerializedName("experience")
    val experience: List<Experience>,
    @SerializedName("gender")
    val gender: List<Gender>,
    @SerializedName("job_search_statuses_applicant")
    val jobSearchStatusesApplicant: List<JobSearchStatusesApplicant>,
    @SerializedName("job_search_statuses_employer")
    val jobSearchStatusesEmployer: List<JobSearchStatusesEmployer>,
    @SerializedName("language_level")
    val languageLevel: List<LanguageLevel>,
    @SerializedName("messaging_status")
    val messagingStatus: List<MessagingStatu>,
    @SerializedName("negotiations_order")
    val negotiationsOrder: List<NegotiationsOrder>,
    @SerializedName("negotiations_participant_type")
    val negotiationsParticipantType: List<NegotiationsParticipantType>,
    @SerializedName("negotiations_state")
    val negotiationsState: List<NegotiationsState>,
    @SerializedName("phone_call_status")
    val phoneCallStatus: List<PhoneCallStatu>,
    @SerializedName("preferred_contact_type")
    val preferredContactType: List<PreferredContactType>,
    @SerializedName("relocation_type")
    val relocationType: List<RelocationType>,
    @SerializedName("resume_access_type")
    val resumeAccessType: List<ResumeAccessType>,
    @SerializedName("resume_contacts_site_type")
    val resumeContactsSiteType: List<ResumeContactsSiteType>,
    @SerializedName("resume_hidden_fields")
    val resumeHiddenFields: List<ResumeHiddenField>,
    @SerializedName("resume_moderation_note")
    val resumeModerationNote: List<ResumeModerationNote>,
    @SerializedName("resume_search_experience_period")
    val resumeSearchExperiencePeriod: List<ResumeSearchExperiencePeriod>,
    @SerializedName("resume_search_fields")
    val resumeSearchFields: List<ResumeSearchField>,
    @SerializedName("resume_search_label")
    val resumeSearchLabel: List<ResumeSearchLabel>,
    @SerializedName("resume_search_logic")
    val resumeSearchLogic: List<ResumeSearchLogic>,
    @SerializedName("resume_search_order")
    val resumeSearchOrder: List<ResumeSearchOrder>,
    @SerializedName("resume_search_relocation")
    val resumeSearchRelocation: List<ResumeSearchRelocation>,
    @SerializedName("resume_status")
    val resumeStatus: List<ResumeStatus>,
    @SerializedName("schedule")
    val schedule: List<Schedule>,
    @SerializedName("travel_time")
    val travelTime: List<TravelTime>,
    @SerializedName("vacancy_billing_type")
    val vacancyBillingType: List<VacancyBillingType>,
    @SerializedName("vacancy_cluster")
    val vacancyCluster: List<VacancyCluster>,
    @SerializedName("vacancy_label")
    val vacancyLabel: List<VacancyLabel>,
    @SerializedName("vacancy_not_prolonged_reason")
    val vacancyNotProlongedReason: List<VacancyNotProlongedReason>,
    @SerializedName("vacancy_relation")
    val vacancyRelation: List<VacancyRelation>,
    @SerializedName("vacancy_search_fields")
    val vacancySearchFields: List<VacancySearchField>,
    @SerializedName("vacancy_search_order")
    val vacancySearchOrder: List<VacancySearchOrder>,
    @SerializedName("vacancy_type")
    val vacancyType: List<VacancyType>,
    @SerializedName("working_days")
    val workingDays: List<WorkingDay>,
    @SerializedName("working_time_intervals")
    val workingTimeIntervals: List<WorkingTimeInterval>,
    @SerializedName("working_time_modes")
    val workingTimeModes: List<WorkingTimeMode>
)

fun DictionariesDTO.toDictionaries(): Dictionaries{
    return Dictionaries(
        jobSearchStatusesApplicant = jobSearchStatusesApplicant,
        driverLicenseTypes = driverLicenseTypes,
        resumeAccessType = resumeAccessType,
        educationLevel = educationLevel,
        relocationType = relocationType,
        languageLevel = languageLevel,
        resumeStatus = resumeStatus,
        employment = employment,
        experience = experience,
        schedule = schedule,
        gender = gender
    )
}