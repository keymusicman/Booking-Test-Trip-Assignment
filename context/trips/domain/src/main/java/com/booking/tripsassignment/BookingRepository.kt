package com.booking.tripsassignment

/**
 * An interface to represent a repository that returns a list of bookings for the given user.
 */
interface BookingRepository {
    /**
     * Returns list of bookings for a given user Id.
     *
     * Assumptions:
     *
     *  - All the bookings returned will belong to that user.
     */
    fun fetchBookings(userId: Int): Result<List<Booking>>
}