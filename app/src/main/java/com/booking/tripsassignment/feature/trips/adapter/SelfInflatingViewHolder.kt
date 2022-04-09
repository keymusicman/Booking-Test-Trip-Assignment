package com.booking.tripsassignment.feature.trips.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class SelfInflatingViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    )