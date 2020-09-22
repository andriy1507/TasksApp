package com.spaceapps.tasks.exoplayer

import android.content.ComponentName
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.view.View
import androidx.fragment.app.Fragment
import com.spaceapps.tasks.core.extensions.viewBinding
import com.spaceapps.tasks.exoplayer.databinding.FragmentMediaPlaybackBinding
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import timber.log.Timber

class MediaPlaybackFragment : Fragment(R.layout.fragment_media_playback) {

    private val binding by viewBinding(FragmentMediaPlaybackBinding::bind)

    private var mediaBrowser: MediaBrowserCompat? = null
    private var mediaController: MediaControllerCompat? = null
    private val controllerCallback = object : MediaControllerCompat.Callback() {
        override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
            Timber.d("onPlaybackStateChanged: ${state?.position}")
            when (state?.actions) {
                PlaybackStateCompat.ACTION_PLAY -> {
                    binding.buttonPlayPause.setImageResource(R.drawable.ic_pause)
                }
                PlaybackStateCompat.ACTION_PAUSE -> {
                    binding.buttonPlayPause.setImageResource(R.drawable.ic_play)
                }
            }
        }

        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomControlView.applySystemWindowInsetsToPadding(bottom = true)
        binding.appBarLayout.applySystemWindowInsetsToPadding(top = true)
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
                        mediaController?.transportControls?.play()
                    }
                }

                override fun onConnectionFailed() {
                    Timber.e("onConnectionFailed")
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