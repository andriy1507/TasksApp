package com.spaceapps.tasks.exoplayer

import android.app.Service
import android.content.Intent
import android.os.Binder
import androidx.core.app.NotificationManagerCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector
import com.spaceapps.tasks.exoplayer.notification.MediaPlayerNotificationsBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MediaPlayerService : Service() {

    val player by lazy {
        ExoPlayer.Builder(
            this,
            MediaCodecAudioRenderer(this, MediaCodecSelector.DEFAULT)
        ).build()
    }

    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    @Inject
    lateinit var notificationBuilder: MediaPlayerNotificationsBuilder

    inner class MediaPLayerBinder : Binder() {
        val service = this@MediaPlayerService
    }

    override fun onBind(intent: Intent?): MediaPLayerBinder {
        return MediaPLayerBinder()
    }
}