package com.spaceapps.tasks.exoplayer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.exoplayer.databinding.FragmentAudioPlayerBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding

@AndroidEntryPoint
class AudioPlayerFragment : BaseFragment(), ServiceConnection {

    override val binding by lazy { FragmentAudioPlayerBinding.inflate(layoutInflater) }

    private val playButton by lazy { binding.playButton }
    private val toolbar by lazy { binding.toolbar }
    private val timeProgressBar by lazy { binding.timeProgressBar }

    private var player: ExoPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applySystemWindowInsets()
        initClickListeners()
        context?.let {
            it.bindService(
                Intent(it, MediaPlayerService::class.java),
                this,
                Context.BIND_AUTO_CREATE
            )
        }
    }

    private fun initExoPlayer() {
        val dataSourceFactory =
            DefaultDataSourceFactory(requireContext(), BuildConfig.LIBRARY_PACKAGE_NAME)
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3"))
        player?.prepare(mediaSource)
        player?.addListener(object : Player.EventListener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                super.onPlayerStateChanged(playWhenReady, playbackState)
                val imageRes = if (playWhenReady) R.drawable.ic_pause else R.drawable.ic_play
                playButton.setImageResource(imageRes)
            }
        })

    }

    private fun initClickListeners() {
        playButton.setOnClickListener {
            player?.apply {
                playWhenReady = !playWhenReady
            }
        }
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }

    private fun applySystemWindowInsets() {
        toolbar.applySystemWindowInsetsToPadding(top = true)
    }

    override fun onServiceConnected(p0: ComponentName?, binder: IBinder?) {
        this.player = (binder as? MediaPlayerService.MediaPLayerBinder)?.service?.player
        initExoPlayer()
    }

    override fun onServiceDisconnected(p0: ComponentName?) = Unit
}