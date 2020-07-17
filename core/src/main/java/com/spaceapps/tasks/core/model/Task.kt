package com.spaceapps.tasks.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task(
    var id:Long = 0,
    var title: String,
    val timestamp: Long,
    var isDone: Boolean,
    val color: Int? = null,
    val icon: Int? = null,
    val subTasks: List<SubTask> = emptyList()
) : DomainEntity, Parcelable