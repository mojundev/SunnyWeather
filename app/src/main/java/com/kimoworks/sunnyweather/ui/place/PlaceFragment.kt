package com.kimoworks.sunnyweather.ui.place

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycommonlib.base.BaseBindingFragment
import com.example.mycommonlib.utils.ToastUtils
import com.kimoworks.sunnyweather.SunnyWeatherApplication
import com.kimoworks.sunnyweather.databinding.FragmentPlaceBinding
import com.kimoworks.sunnyweather.ui.weather.WeatherActivity

class PlaceFragment : BaseBindingFragment<FragmentPlaceBinding>() {

    val viewModel by lazy { ViewModelProvider(this)[PlaceViewModel::class.java] }

    private lateinit var adapter: PlaceAdapter
    override fun initView(view: View) {

        adapter = PlaceAdapter(this, viewModel.placeList)

        if (viewModel.isPlaceSaved()) {
            val place = viewModel.getSavedPlace()
            val intent = Intent(context, WeatherActivity::class.java).apply {
                putExtra("location_lng", place.location.lng)
                putExtra("location_lat", place.location.lat)
                putExtra("place_name", place.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }
        val layoutManager = LinearLayoutManager(activity)
        binding.apply {
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            searchPlaceEdit.addTextChangedListener { editable->
                val content = editable.toString()
                if (content.isNotEmpty()) {
                    viewModel.searchPlaces(content)
                } else {
                    recyclerView.visibility = View.GONE
                    bgImageView.visibility = View.GONE
                    viewModel.placeList.clear()
                    adapter.notifyDataSetChanged()
                }
            }

            viewModel.placeLiveData.observe(this@PlaceFragment) { result->
                val places = result.getOrNull()
                if (places != null) {
                    recyclerView.visibility = View.VISIBLE
                    bgImageView.visibility = View.VISIBLE
                    viewModel.placeList.clear()
                    viewModel.placeList.addAll(places)
                    adapter.notifyDataSetChanged()
                } else {
                    ToastUtils.show(SunnyWeatherApplication.context, "未能查询到任何地点")
                    result.exceptionOrNull()?.printStackTrace()
                }
            }
        }


    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentPlaceBinding.inflate(inflater, container, false)


}