package com.booking.tripsassignment.dsl

import com.booking.tripsassignment.Booking
import com.booking.tripsassignment.Hotel
import com.booking.tripsassignment.Price
import org.joda.time.LocalDate
import java.math.BigDecimal

object Create {
    fun booking(id: String, checkin: Int, checkout: Int) =
        Booking(
            id = id,
            hotel = hotel(),
            checkin = LocalDate.now().plusDays(checkin),
            checkout = LocalDate.now().plusDays(checkout),
            price()
        )

    private fun hotel(): Hotel = Hotel(
        id = Random.string(),
        name = Random.string(),
        mainPhoto = Random.string(),
        cityName = Random.string(),
        cityId = Random.int()
    )

    private fun price(): Price = Price(
        currency = Random.string(),
        amount = BigDecimal(Random.int()),
        scale = Random.int()
    )
}