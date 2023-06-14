package com.example.find_work_it.data.remote.dto.response_list


import android.annotation.SuppressLint
import com.example.find_work_it.domain.model.Response
import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

data class ResponseListDTO(
    @SerializedName("found")
    val found: Int?,
    @SerializedName("items")
    val items: List<Item?>?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("pages")
    val pages: Int?,
    @SerializedName("per_page")
    val perPage: Int?
)

fun Item.toResponse(): Response{
    return Response(
        id = id,
        status = state,
        resume = resume,
        vacancy = vacancy,
        createdAt = dateToStringFormat(createdAt),
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