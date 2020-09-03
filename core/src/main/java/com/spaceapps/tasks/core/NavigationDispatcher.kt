package com.spaceapps.tasks.core

import androidx.navigation.NavDirections
import kotlinx.coroutines.channels.Channel

object NavigationDispatcher {

    val navigationActions = Channel<NavDirections>()

    fun navigate(dest: NavDirections) = navigationActions.offer(dest)

}