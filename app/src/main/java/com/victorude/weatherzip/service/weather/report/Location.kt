package com.victorude.weatherzip.service.weather.report

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val city: String,
    val country: String,
    val distance: Float,
    val latitude: Double,
    val longitude: Double,
    val observation: List<Observation>,
    val state: String,
    val timezone: Int
) : Parcelable