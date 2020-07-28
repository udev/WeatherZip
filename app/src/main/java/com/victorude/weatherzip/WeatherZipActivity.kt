package com.victorude.weatherzip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.victorude.weatherzip.ui.weather.CurrentWeatherFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherZipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CurrentWeatherFragment.newInstance())
                    .commitNow()
        }
    }
}