package com.spaceapps.tasks

import android.os.Build

fun debug(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}

fun oreo(block: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        block()
    }
}