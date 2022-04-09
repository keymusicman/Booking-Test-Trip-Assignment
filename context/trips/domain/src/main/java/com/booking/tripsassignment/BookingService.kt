package com.booking.tripsassignment

import org.joda.time.LocalDate

class BookingService(
    private val repository: BookingRepository
) {
    fun getAllChains(userId: Int): List<List<Booking>> {
        return when (val result = repository.fetchBookings(userId)) {
            is Result.Success -> result.data.toChains()
            is Result.Error -> throw result.exception
        }
    }

    private fun List<Booking>.toChains(): List<List<Booking>> {
        if (isEmpty()) return emptyList()

        val sorted = sortedBy { it.checkin }
        var currentBooking: Booking = sorted[0]
        var currentList = mutableListOf(currentBooking)
        val result = mutableListOf<List<Booking>>(currentList)
        var i = 1
        while (true) {
            if (i == size) break

            val nextBooking = sorted[i]

            if (currentBooking.checkout.sameDateAs(nextBooking.checkin)) {
                currentList.add(nextBooking)
            } else {
                currentList = mutableListOf(nextBooking)
                result.add(currentList)
            }
            currentBooking = nextBooking

            i++
        }
        return result
    }

    private fun LocalDate.sameDateAs(date: LocalDate) =
        toDateTimeAtStartOfDay() == date.toDateTimeAtStartOfDay()
}