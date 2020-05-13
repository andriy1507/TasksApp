package com.spaceapps.tasks.core_ui

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

fun View.visibleIf(expression: () -> Boolean) {
    visibility = if (expression()) VISIBLE else GONE
}

fun View.visibleIf(expression: Boolean) {
    visibility = if (expression) VISIBLE else GONE
}