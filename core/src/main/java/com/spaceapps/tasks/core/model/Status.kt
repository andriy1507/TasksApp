package com.spaceapps.tasks.core.model

sealed class Status {
    object Loading : Status()
    class Success(val data: Any? = null) : Status()
    class Error(val error: Exception) : Status()
}