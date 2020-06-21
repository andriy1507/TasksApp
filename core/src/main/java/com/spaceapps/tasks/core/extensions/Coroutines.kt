package com.spaceapps.tasks.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapps.tasks.core.model.Status
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

suspend fun safeAsync(async: suspend () -> Any): Status {
    return try {
        Status.Success(async())
    } catch (e: Exception) {
        Status.Error(e)
    }
}

fun ViewModel.asyncIO(async: suspend () -> Unit) {
    viewModelScope.launch(IO) {
        async()
    }
}
