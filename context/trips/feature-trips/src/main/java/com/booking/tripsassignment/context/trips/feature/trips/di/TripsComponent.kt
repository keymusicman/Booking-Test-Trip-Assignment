package com.booking.tripsassignment.context.trips.feature.trips.di

import androidx.fragment.app.Fragment
import com.booking.tripsassignment.context.trips.feature.trips.TripsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(
    dependencies = [TripsComponentDependencies::class],
    modules = [TripsModule::class]
)
interface TripsComponent {
    fun inject(fragment: TripsFragment)

    @Component.Builder
    interface Builder {
        fun withUserId(@BindsInstance @Named(TripsModule.NAMED_USER_ID) userId: Int): Builder

        fun withDependencies(deps: TripsComponentDependencies): Builder

        fun withFragment(@BindsInstance fragment: Fragment): Builder

        fun build(): TripsComponent
    }
}