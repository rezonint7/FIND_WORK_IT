package com.example.find_work_it.data.remote.dto.vacancy


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.find_work_it.domain.model.Vacancy
import com.example.find_work_it.domain.model.VacancyDetail
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

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
    val items: List<Item>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("per_page")
    val perPage: Int
)

@RequiresApi(Build.VERSION_CODES.O)
fun Item.toVacancy() : Vacancy {
    return Vacancy(
        idVacancy = id,
        nameVacancy = name,
        employer = employer,
        salary =  salary,
        areaName = area.name,
        publishDate = dateToStringFormat(publishedAt),
    )
}
@RequiresApi(Build.VERSION_CODES.O)
fun Item.toVacancyDetail() : VacancyDetail{
    return VacancyDetail(
        idVacancy = id,
        nameVacancy = name,
        employer = employer,
        salary =  salary,
        address = address,
        publishDate = dateToStringFormat(publishedAt),
        schedule = schedule.name,
        contacts = contacts
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SimpleDateFormat")
fun dateToStringFormat(date : String) : String{
    //2023-04-04T14:50:34+0300
//    val indexOfDate = date.indexOf('T')
//    date.substring(0..indexOfDate)
//    val dateFormat = SimpleDateFormat("yyyy-MM-dd").parse(date)

    val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
    return OffsetDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME).format(formatter)
}