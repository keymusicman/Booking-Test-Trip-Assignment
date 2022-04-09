package com.booking.tripsassignment.feature.trips.adapter

import android.view.ViewGroup
import android.widget.TextView
import com.booking.tripsassignment.R
import com.booking.tripsassignment.feature.trips.TripVO

class TitleViewHolder(parent: ViewGroup) :
    SelfInflatingViewHolder(parent, R.layout.trips_header_item_layout) {
    private val title: TextView by lazy { itemView.findViewById(R.id.trips_header) }

    fun bind(item: TripVO.TitleVO) {
        title.text =
            itemView.context.getString(
                if (item.isUpcoming) {
                    R.string.trip_header_upcoming_trips
                } else {
                    R.string.trip_header_past_trips
                }
            )
    }
}