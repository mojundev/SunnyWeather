package com.kimoworks.sunnyweather

import android.util.Log
import androidx.lifecycle.liveData
import com.kimoworks.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        runBlocking {
            val result = SunnyWeatherNetwork.placeService.searchPlaces("成都")
            println("testme-ddd: " + result.toString())
            
        }
    }
}