package com.spaceapps.tasks.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapps.tasks.core.model.Status
import com.spaceapps.tasks.core.model.Status.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async

suspend fun <T> safeAsync(async: suspend () -> T): Status<T> {
    return try {
        Status.success(async())
    } catch (e: Exception) {
        Status.error(e)
    }
}

fun <T> Status<T>.onSuccess(block: (data: T) -> Unit): Status<T> {
    if (this is Success) {
        block(this.data)
    }
    return this
}

fun <T> Status<T>.onError(block: (error: Exception) -> Unit): Status<T> {
    if (this is Error) {
        block(this.error)
    }
    return this
}

fun <T> Status<T>.onLoading(block: () -> Unit): Status<T> {
    if (this is Loading) {
        block()
    }
    return this
}

fun ViewModel.async(async: suspend () -> Unit) {
    viewModelScope.async(IO) {
        async()
    }
}
