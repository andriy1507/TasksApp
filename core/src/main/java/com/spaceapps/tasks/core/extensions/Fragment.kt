package com.spaceapps.tasks.core.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.spaceapps.tasks.core.NavigationDispatcher

fun <T : Any> Fragment.observeNullable(liveData: LiveData<T?>, action: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, { data -> data?.let { action(it) } })
}

fun <T : Any> Fragment.observe(liveData: LiveData<T>, action: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, { data -> data?.let { action(it) } })
}

fun Fragment.navigate(destination: NavDirections) {
    NavigationDispatcher.navigate(destination)
}

fun <T : ViewBinding> Fragment.viewBinding(factory: (View) -> T) =
    FragmentViewBindingDelegate(this, factory)