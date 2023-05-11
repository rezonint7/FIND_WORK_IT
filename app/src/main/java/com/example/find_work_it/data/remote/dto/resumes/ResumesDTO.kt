package com.example.find_work_it.data.remote.dto.resumes


import android.annotation.SuppressLint
import com.example.find_work_it.data.remote.dto.resumes.models.Item
import com.example.find_work_it.data.remote.dto.vacancy.VacancyDTO
import com.example.find_work_it.domain.model.Resume
import com.example.find_work_it.domain.model.Vacancy
import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

data class ResumesDTO(
    @SerializedName("found")
    val found: Int?,
    @SerializedName("items")
    val items: List<Item>?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("pages")
    val pages: Int?,
    @SerializedName("per_page")
    val perPage: Int?
)

fun ResumesDTO.pagesResumes() : Map<String?, Int?>{
    return mapOf(
        "page" to page,
        "pages" to pages
    )
}

fun Item.toResume(pages: Int = 0, page: Int = 0) : Resume {
    return Resume(
        id = id,
        title = title,
        photo = photo,
        status = status,
        finished = finished,
        updatedAt = dateToStringFormat(updatedAt)
    )
}

@SuppressLint("SimpleDateFormat")
fun dateToStringFormat(date : String?) : String?{
    if(date.isNullOrBlank()) return null
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
    val dateTime = ZonedDateTime.parse(date, inputFormatter)

    val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy в HH:mm", Locale("ru", "RU"))

    return outputFormatter.format(dateTime)
}