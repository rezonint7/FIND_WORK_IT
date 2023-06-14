package com.example.find_work_it.data.remote.dto.response_list


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("building")
    val building: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("description")
    val description: Any?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lng")
    val lng: Double?,
    @SerializedName("metro")
    val metro: Metro?,
    @SerializedName("metro_stations")
    val metroStations: List<MetroStation?>?,
    @SerializedName("raw")
    val raw: String?,
    @SerializedName("street")
    val street: String?
)