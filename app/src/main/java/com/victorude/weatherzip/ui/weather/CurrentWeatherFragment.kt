package com.victorude.weatherzip.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.squareup.picasso.Picasso
import com.victorude.weatherzip.BuildConfig
import com.victorude.weatherzip.databinding.FragmentCurrentWeatherBinding
import com.victorude.weatherzip.service.weather.report.DestinationWeatherReport
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private var b: FragmentCurrentWeatherBinding? = null
    private val binding: FragmentCurrentWeatherBinding
        get() = b!!
    private val viewModel: CurrentWeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentCurrentWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.progressBar.visibility = View.VISIBLE
        viewModel.update() { r: MutableLiveData<DestinationWeatherReport> ->
            r.value?.let { report ->
                binding.progressBar.visibility = View.GONE
                val info = report.observations.location[0]
                val location = "${info.city}, ${info.state}"
                binding.location.text = location
                binding.description.text = info.observation[0].description
                binding.temperature.text = info.observation[0].temperature

                // Not ideal. The API key requirement makes using BindingAdapter difficult.
                val url = "${info.observation[0].iconLink}?apiKey=${BuildConfig.API_KEY}"
                Picasso.get().load(url).into(binding.weatherIcon)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        b = null    // prevent leaking memory
    }
}