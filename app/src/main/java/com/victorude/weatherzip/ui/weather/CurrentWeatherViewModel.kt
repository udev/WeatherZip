package com.victorude.weatherzip.ui.weather

import androidx.databinding.Bindable
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.victorude.weatherzip.BR
import com.victorude.weatherzip.BuildConfig
import com.victorude.weatherzip.service.weather.DestinationWeatherService
import com.victorude.weatherzip.service.weather.report.DestinationWeatherReport
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class CurrentWeatherViewModel @ViewModelInject constructor(
    private val destinationWeatherService: DestinationWeatherService,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ObservableViewModel() {

    @get:Bindable
    @set:Bindable
    var zipCode: String = "78701"
        set(value) {
            field = value
            notifyPropertyChanged(BR.zipCode)
        }

    @get:Bindable
    @set:Bindable
    var location = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.location)
        }

    @get:Bindable
    @set:Bindable
    var description = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    @get:Bindable
    @set:Bindable
    var temperature = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.temperature)
        }

    private fun getWeatherReport(
        product: String = "observation",
        name: String = "Austin, TX"
    ): Call<DestinationWeatherReport> {
        return destinationWeatherService.getWeatherReport(
            mapOf(
                "apiKey" to BuildConfig.API_KEY,
                "product" to product,
                "name" to name
            )
        )
    }

    fun update(function: (MutableLiveData<DestinationWeatherReport>) -> Unit) {
        getWeatherReport().enqueue(object : Callback<DestinationWeatherReport> {
            override fun onFailure(call: Call<DestinationWeatherReport>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(
                call: Call<DestinationWeatherReport>,
                response: Response<DestinationWeatherReport>
            ) {
                response.body()?.let { report ->
                    function.invoke(MutableLiveData(report))
                }
            }
        })
    }
}