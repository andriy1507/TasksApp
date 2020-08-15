package com.spaceapps.tasks.core.extensions

import android.widget.ImageView
import com.spaceapps.tasks.core.R
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?) {
    Picasso.get().load(url).placeholder(R.drawable.img_placeholder).error(R.drawable.img_error).into(this)
}