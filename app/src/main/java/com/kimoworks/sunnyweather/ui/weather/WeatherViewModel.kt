package com.kimoworks.sunnyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.kimoworks.sunnyweather.logic.model.PlaceResponse
import com.kimoworks.sunnyweather.logic.network.Repository

class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<PlaceResponse.Location>()

    /**
     * 它们都是和界面相关的数据，放到ViewModel中可以保证它们在手机屏幕发生旋 转的时候不会丢失
     */
    var locationLng = ""

    var locationLat = ""

    var placeName = ""

    val weatherLiveData = locationLiveData.switchMap {
        Repository.refreshWeather(it.lng, it.lat)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = PlaceResponse.Location(lng, lat)
    }

}