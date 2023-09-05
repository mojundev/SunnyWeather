package com.kimoworks.sunnyweather.logic.network

object SunnyWeatherNetwork {

    val placeService by lazy {
        ServiceCreator.create<PlaceService>()
    }

    val weatherService by lazy {
        ServiceCreator.create<WeatherService>()
    }

}