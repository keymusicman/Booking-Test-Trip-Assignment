package com.booking.tripsassignment.data

import android.os.Looper
import com.booking.tripsassignment.Booking
import com.booking.tripsassignment.BookingRepository
import com.booking.tripsassignment.Result
import kotlin.random.Random

/**
 *  An implementation for the repository which returns bookings based on a generated dataset.
 *  Check [TestCase] class to refer to the different scenarios and which userId will provide bookings for those scenarios.
 */
class MockNetworkBookingRepository : BookingRepository {

    override fun fetchBookings(userId: Int): Result<List<Booking>> {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw RuntimeException("fetchBookings called on main thread!")
        }

        Thread.sleep(Random.nextInt(10, 2000).toLong())
        if (Random.nextInt(0, 21) % 10 == 0) {
            return Result.Error(com.booking.tripsassignment.utils.NetworkError("API call error"))
        }

        val bookings = MockDataGenerator.bookingsForUser(userId)
            ?: return Result.Error(com.booking.tripsassignment.utils.NetworkError("API call error"))
        return Result.Success(bookings)
    }
}