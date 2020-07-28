package com.victorude.weatherzip.service.weather.report

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DestinationWeatherReport(
    val feedCreation: String,
    val metric: Boolean,
    val observations: Observations
) : Parcelable