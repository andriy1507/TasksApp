package com.spaceapps.tasks.exoplayer

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.os.Binder
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaPlayerService : Service() {

    val player by lazy {
        ExoPlayer.Builder(
            this,
            MediaCodecAudioRenderer(this, MediaCodecSelector.DEFAULT)
        ).build()
    }

    private val manager by lazy {
        PlayerNotificationManager.createWithNotificationChannel(
            this,
            getString(R.string.media_player_notifications_channel_id),
            R.string.media_player_notifications_channel_name,
            R.string.media_player_notifications_channel_name,
            12345,
            object : PlayerNotificationManager.MediaDescriptionAdapter {
                override fun getCurrentContentTitle(player: Player) = "Some title"


                override fun createCurrentContentIntent(player: Player): PendingIntent? = null

                override fun getCurrentContentText(player: Player) = "Some text"

                override fun getCurrentLargeIcon(
                    player: Player,
                    callback: PlayerNotificationManager.BitmapCallback
                ): Bitmap? = null
            },
            object : PlayerNotificationManager.NotificationListener {

                override fun onNotificationStarted(
                    notificationId: Int,
                    notification: Notification
                ) = startForeground(notificationId, notification)

                override fun onNotificationCancelled(notificationId: Int) = stopSelf()
            }
        )
    }

    override fun onCreate() {
        super.onCreate()
        manager.setPlayer(player)
    }

    inner class MediaPLayerBinder : Binder() {
        val service = this@MediaPlayerService
    }

    override fun onBind(intent: Intent?): MediaPLayerBinder {
        return MediaPLayerBinder()
    }
}