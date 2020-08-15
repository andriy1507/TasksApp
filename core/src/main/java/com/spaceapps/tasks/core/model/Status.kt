package com.spaceapps.tasks.core.model

sealed class Status {
    object Loading : Status()
    class Success<T:Any>(val data: T) : Status()
    class Error<E : Throwable>(val error: E) : Status()
}