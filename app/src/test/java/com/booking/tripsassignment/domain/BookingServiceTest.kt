package com.booking.tripsassignment.domain

import com.booking.tripsassignment.data.Booking
import com.booking.tripsassignment.dsl.Create
import com.booking.tripsassignment.infrastructure.thenReturn
import com.booking.tripsassignment.repository.BookingRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class BookingServiceTest {
    @Mock
    private lateinit var repository: BookingRepository

    private lateinit var sut: BookingService

    @Before
    fun setUp() {
        sut = BookingService(repository)
    }

    @Test
    fun getAllChains_whenSuccess_andSingleChain_shouldReturnOneChain() {
        val booking1 = Create.booking("1", 1, 3)
        val booking2 = Create.booking("2", 3, 5)
        val booking3 = Create.booking("3", 5, 7)
        whenever(repository.fetchBookings(any())).thenReturn(listOf(booking2, booking1, booking3))

        val chains = sut.getAllChains(0)

        assertEquals(1, chains.size)
        assertChainBookings(chains[0], "1", "2", "3")
    }

    @Test
    fun getAllChains_whenSuccess_andTwoChains_shouldReturnTwoChains() {
        val booking0 = Create.booking(id = "1", checkin = 1, checkout = 3)
        val booking1 = Create.booking(id = "3", checkin = 5, checkout = 6)
        val booking2 = Create.booking(id = "2", checkin = 3, checkout = 5)
        val booking3 = Create.booking(id = "4", checkin = 10, checkout = 12)
        val booking4 = Create.booking(id = "5", checkin = 12, checkout = 13)
        val booking5 = Create.booking(id = "6", checkin = 13, checkout = 16)
        whenever(repository.fetchBookings(any())).thenReturn(
            listOf(booking2, booking1, booking3, booking0, booking5, booking4)
        )

        val chains = sut.getAllChains(0)

        assertEquals(2, chains.size)
        assertChainBookings(chains[0], "1", "2", "3")
        assertChainBookings(chains[1], "4", "5", "6")
    }

    private fun assertChainBookings(chain: List<Booking>, vararg chainIds: String) {
        assertEquals(chainIds.size, chain.size)
        chainIds.forEachIndexed { index, id ->
            assertEquals(id, chain[index].id)
        }
    }
}