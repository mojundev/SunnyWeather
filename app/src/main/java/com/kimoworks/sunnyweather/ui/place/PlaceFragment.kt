package com.kimoworks.sunnyweather.ui.place

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

class PlaceFragment : BaseBindingFragment<FragmentPlaceBinding>() {

    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }

    private lateinit var adapter: PlaceAdapter
    override fun initView(view: View) {
        adapter = PlaceAdapter(this, viewModel.placeList)
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