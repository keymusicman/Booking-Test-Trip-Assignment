package com.booking.tripsassignment.context.trips.feature.trips

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.booking.tripsassignment.context.trips.feature.trips.adapter.TripsAdapter
import com.booking.tripsassignment.context.trips.feature.trips.di.DaggerTripsComponent
import com.booking.tripsassignment.context.trips.feature.trips.di.TripsComponent
import com.booking.tripsassignment.di.findDependencies
import com.booking.tripsassignment.utils.showOnly
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class TripsFragment : Fragment(R.layout.trips_list_screen) {
    private val contentContainer: ViewGroup by lazy { requireView().findViewById(R.id.content_container) }
    private val recyclerView: RecyclerView by lazy { requireView().findViewById(R.id.recyclerview) }
    private val noContent: View by lazy { requireView().findViewById(R.id.empty) }
    private val loadingContent: View by lazy { requireView().findViewById(R.id.loading) }
    private val errorContent: View by lazy { requireView().findViewById(R.id.error) }
    private val btnReload: View by lazy { requireView().findViewById(R.id.btn_reload) }

    private lateinit var component: TripsComponent

    @Inject
    lateinit var viewModel: TripsViewModel

    override fun onAttach(context: Context) {
        component = DaggerTripsComponent
            .builder()
            .withUserId(requireArguments().getInt(USER_ID))
            .withFragment(this)
            .withDependencies(findDependencies())
            .build()
        component.inject(this)

        super.onAttach(context)
    }

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
        private const val USER_ID = "user_id"

        fun newInstance(userId: Int) = TripsFragment().apply {
            arguments = bundleOf(
                USER_ID to userId
            )
        }
    }
}