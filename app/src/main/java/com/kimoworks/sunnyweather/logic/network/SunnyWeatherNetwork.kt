package com.kimoworks.sunnyweather.logic.network

object SunnyWeatherNetwork {

    val placeService by lazy {
        ServiceCreator.create<PlaceService>()
    }

}