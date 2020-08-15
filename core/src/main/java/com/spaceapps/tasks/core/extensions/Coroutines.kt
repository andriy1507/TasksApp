package com.spaceapps.tasks.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapps.tasks.core.model.Status
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async

suspend fun safeAsync(async: suspend () -> Any): Status {
    return try {
        Status.Success(async())
    } catch (e: Exception) {
        Status.Error(e)
    }
}

fun ViewModel.async(async: suspend () -> Unit) {
    viewModelScope.async(IO) {
        async()
    }
}
