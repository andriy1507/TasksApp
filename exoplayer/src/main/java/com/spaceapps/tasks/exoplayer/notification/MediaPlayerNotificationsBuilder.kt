package com.spaceapps.tasks.exoplayer.notification

import android.app.Notification

interface MediaPlayerNotificationsBuilder {

    fun build(): Notification?
}