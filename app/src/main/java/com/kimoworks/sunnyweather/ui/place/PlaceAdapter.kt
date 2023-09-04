package com.kimoworks.sunnyweather.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.kimoworks.sunnyweather.databinding.ItemPlaceBinding
import com.kimoworks.sunnyweather.logic.model.Place

class PlaceAdapter(private val fragment: Fragment,
private val placeList: List<Place>
) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    inner class PlaceViewHolder(val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.binding.apply {
            val place = placeList[position]
            placeName.text = place.name
            placeAddress.text = place.address
        }
    }

    override fun getItemCount() = placeList.size

}