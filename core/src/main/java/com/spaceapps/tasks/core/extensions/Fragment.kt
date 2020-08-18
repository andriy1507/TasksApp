package com.spaceapps.tasks.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

fun <T : Any> Fragment.observeNullable(liveData: LiveData<T?>, action: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer { data -> data?.let { action(it) } })
}

fun <T : Any> Fragment.observe(liveData: LiveData<T>, action: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer { data -> data?.let { action(it) } })
}

fun Fragment.navigate(destination: NavDirections) {
    findNavController().navigate(destination)
}