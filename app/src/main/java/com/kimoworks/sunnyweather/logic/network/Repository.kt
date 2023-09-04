package com.kimoworks.sunnyweather.logic.network

import android.util.Log
import androidx.lifecycle.liveData
import com.kimoworks.sunnyweather.logic.model.Place
import kotlinx.coroutines.Dispatchers

object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.placeService.searchPlaces(query)
            Log.v("test110", placeResponse.toString())
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

}