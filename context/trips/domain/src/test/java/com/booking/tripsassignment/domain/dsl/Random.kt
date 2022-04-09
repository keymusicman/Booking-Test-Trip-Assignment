package com.booking.tripsassignment.domain.dsl

import java.util.*
import kotlin.random.Random

object Random {
    private val random = Random.Default

    fun string(): String = UUID.randomUUID().toString()
    fun int() = random.nextInt()
}