package com.victorude.weatherzip.service.weather.report

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Observations(
    val location: List<Location>
) : Parcelable