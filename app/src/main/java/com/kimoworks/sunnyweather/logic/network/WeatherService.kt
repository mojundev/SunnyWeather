package com.kimoworks.sunnyweather.logic.network

import com.kimoworks.sunnyweather.SunnyWeatherApplication
import com.kimoworks.sunnyweather.logic.model.DailyResponse
import com.kimoworks.sunnyweather.logic.model.RealtimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("v2.6/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime")
    suspend fun getRealtime(@Path("lng") lng: String, @Path("lat") lat: String) : RealtimeResponse

    @GET("v2.6/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily")
    suspend fun getDaily(@Path("lng") lng: String, @Path("lat") lat: String) : DailyResponse

}