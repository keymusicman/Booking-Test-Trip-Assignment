package com.booking.tripsassignment.di

import androidx.fragment.app.Fragment

interface DependencyProvider {
    fun <T> getDependencies(clazz: Class<T>): T
}

inline fun <reified T> Fragment.findDependencies(): T =
    (activity?.application as? DependencyProvider)?.getDependencies(T::class.java)
        .let(::requireNotNull)