package com.spaceapps.tasks.core.model

data class Task(
    var title: String,
    var text: String,
    val timestamp: Long,
    var isDone: Boolean
) : DomainEntity