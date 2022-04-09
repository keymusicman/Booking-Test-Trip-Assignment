package com.booking.tripsassignment

import android.app.Application
import com.booking.tripsassignment.context.trips.feature.trips.di.TripsComponentDependencies
import com.booking.tripsassignment.data.MockNetworkBookingRepository
import com.booking.tripsassignment.di.DependencyProvider

/**
 * Application class. Modify it if required.
 */
class MainApplication : Application(), DependencyProvider {
    private val tripsDependency = object : TripsComponentDependencies {
        override val bookingService: BookingService by lazy {
            BookingService(MockNetworkBookingRepository())
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> getDependencies(clazz: Class<T>): T {
        // here should be dagger or any other implementation of dependency management

        if (clazz == TripsComponentDependencies::class.java) return tripsDependency as T
        else error("Unknown dependency $clazz")
    }
}