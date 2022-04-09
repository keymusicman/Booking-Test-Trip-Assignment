package com.booking.tripsassignment.context.trips.feature.trips.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.booking.tripsassignment.context.trips.feature.trips.TripVO

class TripsAdapter(private val items: List<TripVO>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> TitleViewHolder(parent)
            2 -> TripViewHolder(parent)
            else -> error("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (val item = items[position]) {
            is TripVO.TitleVO -> (holder as TitleViewHolder).bind(item)
            is TripVO.TripItemVO -> (holder as TripViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is TripVO.TitleVO -> 1
            is TripVO.TripItemVO -> 2
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}