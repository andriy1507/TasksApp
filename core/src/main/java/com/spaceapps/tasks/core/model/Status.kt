package com.spaceapps.tasks.core.model

sealed class Status<out T> {
    object Loading : Status<Nothing>()
    class Success<out T>(val data: T) : Status<T>()
    class Error(val error: Exception) : Status<Nothing>()
}