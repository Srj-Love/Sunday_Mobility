package com.example.sundaymobility.utils

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.sundaymobility.R
import com.squareup.picasso.Picasso

// load Images
fun ImageView.loadImage(
    context: Context,
    url: String?,
    defaultImage: Int,
    isBaseUrlAdded: Boolean = false
) {
    url?.let {
        Picasso.with(context)
            .load(url)
            .placeholder(R.drawable.sunday_mob)
            .fit()
            .error(R.drawable.sunday_mob)
            .into(this)
    }
}