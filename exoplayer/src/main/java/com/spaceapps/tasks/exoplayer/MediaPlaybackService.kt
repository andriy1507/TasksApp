package com.spaceapps.tasks.exoplayer

import android.net.Uri
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class MediaPlaybackService : MediaBrowserServiceCompat() {

    private var mediaSession: MediaSessionCompat? = null
    private var exoPlayer: ExoPlayer? = null
    private val callback = object : MediaSessionCompat.Callback() {
        override fun onPlay() {
            exoPlayer?.playWhenReady = true
        }

        override fun onPause() {
            exoPlayer?.playWhenReady = false
        }

        override fun onStop() {
            exoPlayer?.stop()
        }

        override fun onSkipToNext() {
            exoPlayer?.next()
        }

        override fun onSkipToPrevious() {
            if (exoPlayer?.hasPrevious() == true) {
                exoPlayer?.previous()
            } else {
                exoPlayer?.seekTo(0)
            }
        }

        override fun onSeekTo(pos: Long) {
            exoPlayer?.seekTo(pos)
        }
    }

    override fun onCreate() {
        super.onCreate()
        mediaSession = MediaSessionCompat(this, javaClass.simpleName)
        mediaSession?.apply {
            setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)
            setCallback(callback)
            isActive = true
        }
        exoPlayer = SimpleExoPlayer.Builder(this).build()
        val uri =
            "https://firebasestorage.googleapis.com/v0/b/spaceapps-tasks-30ed9.appspot.com/o/Top%20Features%20in%20Android%2011.mp3?alt=media&token=277488f9-3fdc-4c74-a547-fac635780dca"
        val factory = DefaultDataSourceFactory(this, BuildConfig.LIBRARY_PACKAGE_NAME)
        val mediaSource = ProgressiveMediaSource.Factory(factory).createMediaSource(Uri.parse(uri))
        exoPlayer?.prepare(mediaSource)
    }


    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ) = BrowserRoot(PLAYER_ROOT_ID, null)

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        result.sendResult(null)
    }

    companion object {
        const val PLAYER_ROOT_ID = "TasksApp Media"
    }
}