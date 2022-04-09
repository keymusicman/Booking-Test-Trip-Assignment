package com.booking.tripsassignment.context.trips.feature.trips

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TripsViewModel(
    private val service: TripsVOService,
    private val userId: Int
) : ViewModel() {
    private val _uiState = MutableStateFlow<State>(State.Loading)
    val uiState: StateFlow<State> = _uiState

    init {
        reload()
    }

    fun reload() {
        viewModelScope.launch {
            try {
                _uiState.value = State.Loading

                val data = service.getTrips(userId)

                if (data.isEmpty()) {
                    _uiState.value = State.Empty
                } else {
                    _uiState.value = State.Data(data)
                }
            } catch (e: Exception) {
                Log.w("TripsViewModel", "Failed to load data. ", e)
                _uiState.value = State.Error
            }
        }
    }
}

sealed class State {
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
        val numberOfBookings: Int,
        val photo: String
    ) : TripVO()
}