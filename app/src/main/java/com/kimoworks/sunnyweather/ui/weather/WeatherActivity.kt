package com.kimoworks.sunnyweather.ui.weather

import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.lifecycle.ViewModelProvider
import com.example.mycommonlib.base.BaseBindingActivity
import com.example.mycommonlib.utils.ToastUtils
import com.kimoworks.sunnyweather.R
import com.kimoworks.sunnyweather.databinding.ActivityWeatherBinding
import com.kimoworks.sunnyweather.databinding.NowBinding
import com.kimoworks.sunnyweather.logic.model.Weather
import com.kimoworks.sunnyweather.logic.model.getSky
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherActivity : BaseBindingActivity<ActivityWeatherBinding>() {

    val viewModel by lazy {
        ViewModelProvider(this)[WeatherViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun initView() {

        val decorView = window.decorView


        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        window.statusBarColor = Color.TRANSPARENT
//        val controller = window.insetsController
//        controller?.let {
//            it.hide(WindowInsetsCompat.Type.statusBars())
//            it.controlWindowInsetsAnimation()
//        }

        if (viewModel.locationLng.isEmpty()) {
            viewModel.locationLng = intent.getStringExtra("location_lng") ?: ""
        }
        if (viewModel.locationLat.isEmpty()) {
            viewModel.locationLat = intent.getStringExtra("location_lat") ?: ""
        }
        if (viewModel.placeName.isEmpty()) {
            viewModel.placeName = intent.getStringExtra("place_name") ?: ""
        }
        viewModel.weatherLiveData.observe(this) { result->
            val weather = result.getOrNull()
            if (weather != null) {
                showWeatherInfo(weather)
            } else {
                ToastUtils.show(this,"无法成功获取天气信息")
                result.exceptionOrNull()?.printStackTrace()
            }
            binding.swipeRefresh.isRefreshing = false
        }

        refreshWeather()
        binding.apply {
            swipeRefresh.setOnRefreshListener { refreshWeather() }
            now.navBtn.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            drawerLayout.addDrawerListener(object : DrawerListener {
                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

                }

                override fun onDrawerOpened(drawerView: View) {

                }

                override fun onDrawerClosed(drawerView: View) {
                    val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    manager.hideSoftInputFromWindow(drawerView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                }

                override fun onDrawerStateChanged(newState: Int) {

                }

            })
        }
    }

    fun closeDrawer() {
        binding.drawerLayout.closeDrawers()
    }

    fun refreshWeather() {
        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)
        binding.swipeRefresh.isRefreshing = true
    }

    private fun showWeatherInfo(weather: Weather) {
        binding.apply {

            val daily = weather.daily
            now.apply {
                placeName.text = viewModel.placeName
                val realtime = weather.realtime

// 填充now.xml布局中的数据
                val currentTempText = "${realtime.temperature.toInt()} °C"
                currentTemp.text = currentTempText
                currentSky.text = getSky(realtime.skycon).info
                val currentPM25Text = "空气指数 ${realtime.airQuality.aqi.chn.toInt()}"
                currentAQI.text = currentPM25Text
                nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)
            }
// 填充forecast.xml布局中的数据
            forecast.apply {
                forecastLayout.removeAllViews()
                val days = daily.skycon.size
                for (i in 0 until days) {

                    val skycon = daily.skycon[i]
                    val temperature = daily.temperature[i]
                    val view = LayoutInflater.from(this@WeatherActivity).inflate(R.layout.item_forecast,
                        forecastLayout, false)
                    val dateInfo = view.findViewById(R.id.dateInfo) as TextView
                    val skyIcon = view.findViewById(R.id.skyIcon) as ImageView
                    val skyInfo = view.findViewById(R.id.skyInfo) as TextView
                    val temperatureInfo = view.findViewById(R.id.temperatureInfo) as TextView
                    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    dateInfo.text = simpleDateFormat.format(skycon.date)
                    val sky = getSky(skycon.value)
                    skyIcon.setImageResource(sky.icon)
                    skyInfo.text = sky.info
                    val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()} °C"
                    temperatureInfo.text = tempText
                    forecastLayout.addView(view)
                }
            }

// 填充life_index.xml布局中的数据
            lifeIndex.apply {
                val lifeIndex = daily.lifeIndex
                coldRiskText.text = lifeIndex.coldRisk[0].desc
                dressingText.text = lifeIndex.dressing[0].desc
                ultravioletText.text = lifeIndex.ultraviolet[0].desc
                carWashingText.text = lifeIndex.carWashing[0].desc
                weatherLayout.visibility = View.VISIBLE
            }

        }
    }

    override fun getViewBinding(): ActivityWeatherBinding = ActivityWeatherBinding.inflate(layoutInflater)
}