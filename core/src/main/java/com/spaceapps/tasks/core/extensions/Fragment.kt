package com.spaceapps.tasks.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any> Fragment.observeNullable(liveData: LiveData<T?>, action: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer { data -> data?.let { action(it) } })
}

fun <T : Any> Fragment.observe(liveData: LiveData<T>, action: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer { data -> data?.let { action(it) } })
}