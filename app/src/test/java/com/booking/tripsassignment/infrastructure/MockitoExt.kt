package com.booking.tripsassignment.infrastructure

import com.booking.tripsassignment.Result
import org.mockito.stubbing.OngoingStubbing

fun <T : Any?> OngoingStubbing<Result<T>>.thenReturn(value: T) =
    thenReturn(Result.Success(value))