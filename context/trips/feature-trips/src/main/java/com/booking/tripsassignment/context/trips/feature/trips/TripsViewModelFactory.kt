package com.booking.tripsassignment.context.trips.feature.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

class TripsViewModelFactory(
    private val serviceProvider: Provider<TripsVOService>,
    private val userId: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TripsViewModel(serviceProvider.get(), userId) as T
    }
}