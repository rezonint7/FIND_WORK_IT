package com.example.find_work_it.data.remote.dto.vacancy


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("building")
    val building: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("metro_stations")
    val metroStations: List<MetroStation>,
    @SerializedName("street")
    val street: String
)