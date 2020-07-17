package com.spaceapps.tasks.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubTask(
    val id:Long = 0,
    val text: String = "",
    val isDone: Boolean = false
) : DomainEntity, Parcelable