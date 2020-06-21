package com.spaceapps.tasks

fun debug(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}