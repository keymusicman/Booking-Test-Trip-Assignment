package com.booking.tripsassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.booking.tripsassignment.feature.trips.TripsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, TripsFragment.newInstance())
            .commit()
    }
}