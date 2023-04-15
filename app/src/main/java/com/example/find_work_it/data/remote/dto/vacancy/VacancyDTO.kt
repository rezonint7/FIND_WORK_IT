package com.example.find_work_it.data.remote.dto.vacancy


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.find_work_it.data.remote.dto.vacancy.models.Item
import com.example.find_work_it.domain.model.Vacancy
import com.example.find_work_it.domain.model.VacancyDetail
import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class VacancyDTO(
    @SerializedName("alternate_url")
    val alternateUrl: String,
    @SerializedName("arguments")
    val arguments: Any?,
    @SerializedName("clusters")
    val clusters: Any?,
    @SerializedName("found")
    val found: Int,
    @SerializedName("items")
    val items: List<Item?>?,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("per_page")
    val perPage: Int
)

fun Item.toVacancy() : Vacancy {
    return Vacancy(
        idVacancy = id,
        nameVacancy = name,
        employer = employer,
        salary =  salary,
        areaName = address?.city,
        publishDate = dateToStringFormat(publishedAt),
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