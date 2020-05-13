package com.spaceapps.tasks.core.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task(
    var title: String,
    var text: String,
    val timestamp: Long,
    var isDone: Boolean,
    val color: Int? = null,
    val icon: Int? = null
) : DomainEntity, Parcelable