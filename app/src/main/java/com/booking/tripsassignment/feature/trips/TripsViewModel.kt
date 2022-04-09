package com.booking.tripsassignment.feature.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class TripsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<State>(State.Loading)
    val uiState: StateFlow<State> = _uiState

    init {
        viewModelScope.launch {
            try {
                // TODO - inject
                val data = TripsVOServiceImpl().getTrips()

                if (data.isEmpty()) {
                    _uiState.value = State.Empty
                } else {
                    _uiState.value = State.Data(data)
                }
            } catch (e: Exception) {
                println(e)
                _uiState.value = State.Error
            }
        }
    }
}

internal sealed class State {
    object Loading : State()
    object Empty : State()
    class Data(val trips: List<TripVO>) : State()
    object Error : State()
}

sealed class TripVO {
    class TitleVO(val isUpcoming: Boolean) : TripVO()
    class TripItemVO(
        val chainCities: String,
        val dates: String,
        val numberOfBookings: Int
    ) : TripVO()
}