package com.booking.tripsassignment.feature.trips

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.booking.tripsassignment.R

class TripsFragment : Fragment(R.layout.trips_list_screen) {
    private val viewModel by viewModels<TripsViewModel>()
}