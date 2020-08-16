package com.spaceapps.tasks.logging

import android.annotation.SuppressLint
import android.util.Log
import timber.log.Timber

class DebugTree : Timber.Tree() {
    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.ASSERT -> Log.d("ASSERT - $tag", message, t)
            Log.DEBUG -> Log.d("DEBUG - $tag", message, t)
            Log.INFO -> Log.i("INFO - $tag", message, t)
            Log.VERBOSE -> Log.v("VERBOSE - $tag", message, t)
            Log.WARN -> Log.w("WARN - $tag", message, t)
            Log.ERROR -> Log.e("ERROR - $tag", message, t)
        }
    }
}