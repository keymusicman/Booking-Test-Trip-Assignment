package com.booking.tripsassignment.context.trips.feature.trips

import com.booking.tripsassignment.Booking
import com.booking.tripsassignment.BookingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.joda.time.LocalDate

interface TripsVOService {
    suspend fun getTrips(userId: Int): List<TripVO>
}

class TripsVOServiceImpl(
    private val bookingService: BookingService,
    private val chainDatesFormatter: ChainDatesFormatter
) : TripsVOService {
    override suspend fun getTrips(userId: Int): List<TripVO> = withContext(Dispatchers.IO) {
        val allChains = bookingService.getAllChains(userId)

        val now = LocalDate.now()
        val (upcoming, past) = allChains
            .partition { chain ->
                chain.last().checkout.isAfter(now)
            }

        val result = mutableListOf<TripVO>()

        if (upcoming.isNotEmpty()) {
            result.add(TripVO.TitleVO(true))
            upcoming.map(::toTripVO).also(result::addAll)
        }
        if (past.isNotEmpty()) {
            result.add(TripVO.TitleVO(false))
            past.map(::toTripVO).reversed().also(result::addAll)
        }

        result
    }

    private fun toTripVO(chain: List<Booking>) =
        TripVO.TripItemVO(
            getChainCities(chain),
            chainDatesFormatter.getChainDates(chain),
            chain.size,
            chain.first().hotel.mainPhoto
        )

    private fun getChainCities(chain: List<Booking>): String {
        val cities = chain.distinctBy { it.hotel.cityId }
        return cities
            .map { it.hotel.cityName }
            .foldIndexed("") { index, acc, value ->
                when (index) {
                    0 -> value
                    cities.size - 1 -> "$acc and $value"
                    else -> "$acc, $value"
                }
            }
    }


}