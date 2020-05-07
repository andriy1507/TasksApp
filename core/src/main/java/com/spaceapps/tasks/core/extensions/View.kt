package com.spaceapps.tasks.core.extensions

import android.view.View
import android.view.View.*

fun View.gone() {
    visibility = GONE
}

fun View.invisible() {
    visibility = INVISIBLE
}

fun View.visible() {
    visibility = VISIBLE
}