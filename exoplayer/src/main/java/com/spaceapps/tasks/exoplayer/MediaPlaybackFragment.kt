package com.spaceapps.tasks.exoplayer

import android.content.ComponentName
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

class MediaPlaybackFragment : Fragment(R.layout.fragment_media_playback) {
    private var mediaBrowser: MediaBrowserCompat? = null
    private var mediaController: MediaControllerCompat? = null
    private val controllerCallback = object : MediaControllerCompat.Callback() {
        override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
            Log.d("TAG", "onPlaybackStateChanged: ${state?.position}")
        }

        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaBrowser = MediaBrowserCompat(
            requireContext(),
            ComponentName(requireContext(), MediaPlaybackService::class.java),
            object : MediaBrowserCompat.ConnectionCallback() {
                override fun onConnected() {
                    mediaBrowser?.sessionToken?.let { token ->
                        mediaController = MediaControllerCompat(requireContext(), token)
                        MediaControllerCompat.setMediaController(requireActivity(), mediaController)
                        MediaControllerCompat.getMediaController(requireActivity())
                            .registerCallback(controllerCallback)
                    }
                    mediaController?.transportControls?.play()
                }
            },
            null
        )
        mediaBrowser?.connect()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaBrowser?.disconnect()
    }
}