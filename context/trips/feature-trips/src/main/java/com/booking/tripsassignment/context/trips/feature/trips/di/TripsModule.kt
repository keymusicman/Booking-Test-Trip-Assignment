package com.booking.tripsassignment.context.trips.feature.trips.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.booking.tripsassignment.context.trips.feature.trips.*
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Provider

@Module
class TripsModule {
    companion object {
        const val NAMED_USER_ID = "userId"
    }

    @Provides
    fun provideViewModel(
        fragment: Fragment,
        tripsVOServiceProvider: Provider<TripsVOService>,
        @Named(NAMED_USER_ID) userId: Int
    ): TripsViewModel {
        return ViewModelProvider(
            fragment.viewModelStore,
            TripsViewModelFactory(tripsVOServiceProvider, userId)
        )
            .get(TripsViewModel::class.java)
    }

    @Provides
    fun provideTripsVOService(
        dependencies: TripsComponentDependencies,
        chainDatesFormatter: ChainDatesFormatter
    ): TripsVOService =
        TripsVOServiceImpl(dependencies.bookingService, chainDatesFormatter)

    @Provides
    fun provideChainDatesFormatter() = ChainDatesFormatter()
}