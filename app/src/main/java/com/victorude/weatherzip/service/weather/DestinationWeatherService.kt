package com.victorude.weatherzip.service.weather

import com.victorude.weatherzip.BuildConfig
import com.victorude.weatherzip.service.weather.report.DestinationWeatherReport
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap
import timber.log.Timber

interface DestinationWeatherService {
    @GET("weather/1.0/report.json")
    fun getWeatherReport(@QueryMap params: Map<String, String>): Call<DestinationWeatherReport>
}

@Module
@InstallIn(ActivityComponent::class)
abstract class DestinationApiModule {

    companion object {
        @Provides
        fun providesOkHttp(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request()

                    val t1 = System.nanoTime()
                    Timber.i("\nSending request ${request.url} on ${chain.connection()}\n${request.headers}")

                    val response: Response = chain.proceed(request)

                    val t2 = System.nanoTime()
                    Timber.i("\nReceived response for ${response.request.url} in ${(t2 - t1) / 1e6}%n${response.headers}")

                    response
                }
                .build()
        }

        @Provides
        fun provideDestinationWeatherService(okHttpClient: OkHttpClient): DestinationWeatherService {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DestinationWeatherService::class.java)
        }
    }
}