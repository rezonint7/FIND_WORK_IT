package com.example.find_work_it.domain.model

import com.example.find_work_it.data.remote.dto.resumes.models.Area
import com.example.find_work_it.data.remote.dto.resumes.models.Certificate
import com.example.find_work_it.data.remote.dto.resumes.models.Contact
import com.example.find_work_it.data.remote.dto.resumes.models.Experience
import com.example.find_work_it.data.remote.dto.resumes.models.Gender
import com.example.find_work_it.data.remote.dto.resumes.models.Photo
import com.example.find_work_it.data.remote.dto.resumes.models.Salary
import com.example.find_work_it.data.remote.dto.resumes.models.Status
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Education

data class ResumeDetail(
    val resumeId: String,
    val lastName: String,
    val firstName: String,
    val middleName: String?,
    val birthDate: String?,
    val age: Int?,
    val gender: Gender?,
    val area: Area?,
    val contact: List<Contact>?,
    val title: String?,
    val photo: Photo?,
    val salary: Salary?,
    val education: Education?,
    val experience: List<Experience>?,
    val status: Status,
    val finished: Boolean,
    val skills: String?,
    val skills_set: List<String>?,
    val certificate: List<Certificate>?,
)
