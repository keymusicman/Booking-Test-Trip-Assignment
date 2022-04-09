package com.booking.tripsassignment.feature.trips

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class TripsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<State>(State.Loading)
    val uiState: StateFlow<State> = _uiState
}

internal sealed class State {
    object Loading : State()
    object Empty : State()
    class Data(val trips: List<TripVO>) : State()
    object Error : State()
}

sealed class TripVO {
    object TitleVO : TripVO()
    class TripItemVO() : TripVO()
}