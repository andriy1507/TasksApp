package com.spaceapps.tasks.core.model

sealed class Status<out T> {

    companion object {

        fun loading() = Loading

        fun <T> success(data: T) = Success(data)

        fun error(error: Exception) = Error(error)
    }

    object Loading : Status<Nothing>()
    class Success<out T>(val data: T) : Status<T>()
    class Error(val error: Exception) : Status<Nothing>()
}