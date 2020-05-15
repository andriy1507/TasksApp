package com.spaceapps.tasks.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubTask(
    val text: String,
    val isDone: Boolean
) : DomainEntity, Parcelable