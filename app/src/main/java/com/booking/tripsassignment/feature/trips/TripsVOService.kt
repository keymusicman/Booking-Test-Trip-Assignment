package com.booking.tripsassignment.feature.trips

import com.booking.tripsassignment.domain.Booking
import com.booking.tripsassignment.domain.BookingService
import com.booking.tripsassignment.repository.MockNetworkBookingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

interface TripsVOService {
    suspend fun getTrips(): List<TripVO>
}

class TripsVOServiceImpl : TripsVOService {
    private val formatter = DateTimeFormat.forPattern("dd MMMM, yyyy")

    override suspend fun getTrips(): List<TripVO> = withContext(Dispatchers.IO) {
        // TODO - inject
        val allChains = BookingService(MockNetworkBookingRepository())
            .getAllChains(899848)

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
            past.map(::toTripVO).also(result::addAll)
        }

        result
    }

    private fun toTripVO(chain: List<Booking>) =
        TripVO.TripItemVO(
            getChainCities(chain),
            getChainDates(chain),
            chain.size,
            chain.first().hotel.mainPhoto
        )

    private fun getChainDates(chain: List<Booking>): String {
        val startDate = getStartDate(chain)
        val endDate = getEndDate(chain)

        val dates: String =
            if (startDate.toDateTimeAtStartOfDay() == endDate.toDateTimeAtStartOfDay()) {
                startDate.toString(formatter)
            } else {
                "${startDate.toString(formatter)} - ${endDate.toString(formatter)}"
            }
        return dates
    }

    private fun getChainCities(chain: List<Booking>): String =
        chain.distinctBy { it.hotel.cityId }
            .map { it.hotel.cityName }
            .foldIndexed("") { index, acc, value ->
                when (index) {
                    0 -> value
                    chain.size - 1 -> "$acc and $value"
                    else -> "$acc, $value"
                }
            }

    private fun getStartDate(chain: List<Booking>): LocalDate =
        chain.first().checkin

    private fun getEndDate(chain: List<Booking>): LocalDate =
        chain.last().checkout
}