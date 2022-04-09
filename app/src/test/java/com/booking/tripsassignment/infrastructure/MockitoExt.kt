package com.booking.tripsassignment.infrastructure

import org.mockito.stubbing.OngoingStubbing

fun <T : Any?> OngoingStubbing<com.booking.tripsassignment.utils.Result<T>>.thenReturn(value: T) =
    thenReturn(com.booking.tripsassignment.utils.Result.Success(value))