package com.booking.tripsassignment.context.trips.feature.trips

import com.booking.tripsassignment.Booking
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

class ChainDatesFormatter {
    private val withYearFormatter = DateTimeFormat.forPattern("d MMM yyyy")
    private val withMonthFormatter = DateTimeFormat.forPattern("d MMM")
    private val dayOnlyFormatter = DateTimeFormat.forPattern("d")

    fun getChainDates(chain: List<Booking>): String {
        val startDate = getStartDate(chain)
        val endDate = getEndDate(chain)

        val dates: String =
            when {
                startDate.toDateTimeAtStartOfDay() == endDate.toDateTimeAtStartOfDay() -> {
                    startDate.toString(withYearFormatter)
                }
                startDate.year != endDate.year -> {
                    "${startDate.toString(withYearFormatter)}-${endDate.toString(withYearFormatter)}"
                }
                startDate.monthOfYear != endDate.monthOfYear -> {
                    "${startDate.toString(withMonthFormatter)}-${endDate.toString(withYearFormatter)}"
                }
                else -> {
                    "${startDate.toString(dayOnlyFormatter)}-${endDate.toString(withYearFormatter)}"
                }
            }
        return dates
    }

    private fun getStartDate(chain: List<Booking>): LocalDate =
        chain.first().checkin

    private fun getEndDate(chain: List<Booking>): LocalDate =
        chain.last().checkout
}