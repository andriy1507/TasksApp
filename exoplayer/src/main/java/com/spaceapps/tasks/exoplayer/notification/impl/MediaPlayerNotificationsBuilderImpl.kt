package com.spaceapps.tasks.exoplayer.notification.impl

import android.app.Notification
import android.content.Context
import android.graphics.Color
import androidx.core.app.NotificationCompat
import com.spaceapps.tasks.exoplayer.R
import com.spaceapps.tasks.exoplayer.notification.MediaPlayerNotificationsBuilder
import javax.inject.Inject

class MediaPlayerNotificationsBuilderImpl @Inject constructor(private val context: Context) :
    MediaPlayerNotificationsBuilder {
    override fun build(): Notification? {
        return NotificationCompat.Builder(context, getNotificationsChannel())
            .setSmallIcon(R.drawable.ic_music_note)
            .setColorized(true)
            .setColor(Color.RED)
            .setContentTitle("Media player")
            .setContentText("Media player")
            .build()
    }

    private fun getNotificationsChannel() =
        context.getString(R.string.media_player_notifications_channel)
}