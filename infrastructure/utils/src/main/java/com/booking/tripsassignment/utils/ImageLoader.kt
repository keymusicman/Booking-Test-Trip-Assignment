package com.booking.tripsassignment.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

/**
 * Helper to load images using Glide.
 */
object ImageLoader {

    /**
     * Common method to load and cache images using Glide.
     */
    fun loadImage(view: ImageView, image: String, @DrawableRes errorRes: Int) {
        Glide.with(view)
            .load(image)
            .centerCrop()
            .error(errorRes)
            .into(view)
    }
}