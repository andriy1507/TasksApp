package com.spaceapps.tasks.core.extensions

import com.spaceapps.tasks.core.BuildConfig

fun debug(action: () -> Unit) {
    if (BuildConfig.DEBUG) {
        action()
    }
}