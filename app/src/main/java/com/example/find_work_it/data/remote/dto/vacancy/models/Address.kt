package com.example.find_work_it.data.remote.dto.vacancy.models


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("building")
    val building: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lng")
    val lng: Double?,
    @SerializedName("raw")
    val raw: String?,
    @SerializedName("metro")
    val metro: Metro?,
    @SerializedName("metro_stations")
    val metroStations: List<MetroStation?>?,
    @SerializedName("street")
    val street: String?
)