package com.spaceapps.tasks.core.extensions

import com.spaceapps.tasks.core.model.Status

suspend fun safeAsync(async: suspend () -> Any): Status {
    return try {
        Status.Success(async())
    } catch (e: Exception) {
        Status.Error(e)
    }
}
