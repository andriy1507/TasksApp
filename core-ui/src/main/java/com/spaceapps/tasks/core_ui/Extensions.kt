package com.spaceapps.tasks.core_ui

import android.content.Context
import android.util.TypedValue
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat

@ColorInt
fun Context.getThemeColor(@AttrRes color: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(color, typedValue, true)
    return typedValue.data
}