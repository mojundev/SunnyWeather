package com.kimoworks.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val TOKEN = "WbUzNxCmsIBCn65O"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}