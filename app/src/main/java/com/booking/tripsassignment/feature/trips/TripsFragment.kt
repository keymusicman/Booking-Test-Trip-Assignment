package com.booking.tripsassignment.feature.trips

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.booking.tripsassignment.R
import com.booking.tripsassignment.feature.trips.adapter.TripsAdapter
import com.booking.tripsassignment.utils.showOnly
import kotlinx.coroutines.flow.collect

class TripsFragment : Fragment(R.layout.trips_list_screen) {
    private val viewModel by viewModels<TripsViewModel>()
    private val contentContainer: ViewGroup by lazy { requireView().findViewById(R.id.content_container) }
    private val recyclerView: RecyclerView by lazy { requireView().findViewById(R.id.recyclerview) }
    private val noContent: View by lazy { requireView().findViewById(R.id.empty) }
    private val loadingContent: View by lazy { requireView().findViewById(R.id.loading) }
    private val errorContent: View by lazy { requireView().findViewById(R.id.error) }
    private val btnReload: View by lazy { requireView().findViewById(R.id.btn_reload) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                when (state) {
                    is State.Data -> {
                        recyclerView.adapter = TripsAdapter(state.trips)
                        contentContainer.showOnly(recyclerView)
                    }
                    State.Empty -> {
                        contentContainer.showOnly(noContent)
                    }
                    State.Error -> {
                        contentContainer.showOnly(errorContent)
                    }
                    State.Loading -> {
                        contentContainer.showOnly(loadingContent)
                    }
                }
            }
        }

        btnReload.setOnClickListener {
            viewModel.reload()
        }
    }

    companion object {
        fun newInstance() = TripsFragment()
    }
}