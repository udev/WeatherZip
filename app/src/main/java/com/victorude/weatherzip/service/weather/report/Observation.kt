package com.victorude.weatherzip.service.weather.report

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Observation(
    val activeAlerts: String,
    val ageMinutes: String,
    val airDescription: String,
    val airInfo: String,
    val barometerPressure: String,
    val barometerTrend: String,
    val city: String,
    val comfort: String,
    val country: String,
    val daylight: String,
    val description: String,
    val dewPoint: String,
    val distance: Float,
    val elevation: Int,
    val highTemperature: String,
    val humidity: String,
    val icon: String,
    val iconLink: String,
    val iconName: String,
    val latitude: Double,
    val longitude: Double,
    val lowTemperature: String,
    val precipitation12H: String,
    val precipitation1H: String,
    val precipitation24H: String,
    val precipitation3H: String,
    val precipitation6H: String,
    val precipitationDesc: String,
    val skyDescription: String,
    val skyInfo: String,
    val snowCover: String,
    val state: String,
    val temperature: String,
    val temperatureDesc: String,
    val utcTime: String,
    val visibility: String,
    val windDesc: String,
    val windDescShort: String,
    val windDirection: String,
    val windSpeed: String
) : Parcelable