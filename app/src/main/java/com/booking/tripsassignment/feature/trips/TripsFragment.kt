package com.booking.tripsassignment.feature.trips

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.booking.tripsassignment.R
import com.booking.tripsassignment.feature.trips.adapter.TripsAdapter
import kotlinx.coroutines.flow.collect

class TripsFragment : Fragment(R.layout.trips_list_screen) {
    private val viewModel by viewModels<TripsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                when (state) {
                    is State.Data -> {
                        recyclerView.adapter = TripsAdapter(state.trips)
                    }
                    State.Empty -> {

                    }
                    State.Error -> {

                    }
                    State.Loading -> {

                    }
                }
            }
        }
    }
}