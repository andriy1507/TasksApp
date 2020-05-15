package com.spaceapps.tasks.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task(
    var title: String,
    val timestamp: Long,
    var isDone: Boolean,
    val color: Int? = null,
    val icon: Int? = null
) : DomainEntity, Parcelable