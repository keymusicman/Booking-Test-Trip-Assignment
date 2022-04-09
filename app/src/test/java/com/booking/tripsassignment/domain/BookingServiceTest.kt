package com.booking.tripsassignment.domain

import com.booking.tripsassignment.dsl.Create
import com.booking.tripsassignment.repository.BookingRepository
import com.booking.tripsassignment.utils.Result
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
        whenever(repository.fetchBookings(any())).thenReturn(
            Result.Success(listOf(booking2, booking1, booking3))
        )

        val chains = sut.getAllChains(0)

        assertEquals(1, chains.size)
        chains.first().also { chain ->
            assertEquals(3, chain.size)
            assertEquals("1", chain[0].id)
            assertEquals("2", chain[1].id)
            assertEquals("3", chain[2].id)
        }
    }
}