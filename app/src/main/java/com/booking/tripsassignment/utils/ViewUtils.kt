package com.booking.tripsassignment.utils

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun ViewGroup.hideAllChildren() {
    children.forEach { it.hide() }
}

fun ViewGroup.showOnly(view: View) {
    if (containsView(view)) {
        hideAllChildren()
        view.show()
    }
}

fun ViewGroup.containsView(view: View): Boolean {
    return children.any { child ->
        when (child) {
            view -> true
            is ViewGroup -> child.containsView(view)
            else -> false
        }
    }
}