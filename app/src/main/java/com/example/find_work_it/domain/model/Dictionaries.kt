package com.example.find_work_it.domain.model

import com.example.find_work_it.data.remote.dto.dictionaries.models.DriverLicenseType
import com.example.find_work_it.data.remote.dto.dictionaries.models.EducationLevel
import com.example.find_work_it.data.remote.dto.dictionaries.models.Experience
import com.example.find_work_it.data.remote.dto.dictionaries.models.Gender
import com.example.find_work_it.data.remote.dto.dictionaries.models.JobSearchStatusesApplicant
import com.example.find_work_it.data.remote.dto.dictionaries.models.LanguageLevel
import com.example.find_work_it.data.remote.dto.dictionaries.models.RelocationType
import com.example.find_work_it.data.remote.dto.dictionaries.models.ResumeAccessType
import com.example.find_work_it.data.remote.dto.dictionaries.models.ResumeStatus
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Employment
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Schedule

data class Dictionaries(
    val jobSearchStatusesApplicant: List<JobSearchStatusesApplicant>,
    val driverLicenseTypes: List<DriverLicenseType>,
    val resumeAccessType: List<ResumeAccessType>,
    val educationLevel: List<EducationLevel>,
    val relocationType: List<RelocationType>,
    val languageLevel: List<LanguageLevel>,
    val resumeStatus: List<ResumeStatus>,
    val employment: List<Employment>,
    val experience: List<Experience>,
    val schedule: List<Schedule>,
    val gender: List<Gender>,
)
