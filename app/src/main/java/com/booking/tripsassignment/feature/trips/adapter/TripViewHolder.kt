package com.booking.tripsassignment.feature.trips.adapter

import android.view.ViewGroup
import android.widget.TextView
import com.booking.tripsassignment.R
import com.booking.tripsassignment.feature.trips.TripVO

class TripViewHolder(parent: ViewGroup) :
    SelfInflatingViewHolder(parent, R.layout.trip_card_item_layout) {

    private val title: TextView by lazy { itemView.findViewById(R.id.cities) }
    private val dates: TextView by lazy { itemView.findViewById(R.id.dates) }
    private val bookingsCount: TextView by lazy { itemView.findViewById(R.id.bookings_count) }

    fun bind(item: TripVO.TripItemVO) {
        title.text = itemView.context.getString(R.string.trip_card_title, item.chainCities)
        dates.text = item.dates
        bookingsCount.text = itemView.context.resources.getQuantityString(
            R.plurals.trip_card_bookings_count,
            item.numberOfBookings,
            item.numberOfBookings
        )
    }
}