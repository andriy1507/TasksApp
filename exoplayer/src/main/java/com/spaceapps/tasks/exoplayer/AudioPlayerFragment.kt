package com.spaceapps.tasks.exoplayer

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.exoplayer.databinding.FragmentAudioPlayerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AudioPlayerFragment : BaseFragment() {

    override val binding by lazy { FragmentAudioPlayerBinding.inflate(layoutInflater) }

    private val playerView by lazy { binding.playerView }
    private val playButton by lazy { binding.playButton }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val player = ExoPlayer.Builder(
            requireContext(),
            MediaCodecAudioRenderer(requireContext(), MediaCodecSelector.DEFAULT)
        ).build()
        playerView.player = player
        val dataSourceFactory =
            DefaultDataSourceFactory(requireContext(), BuildConfig.LIBRARY_PACKAGE_NAME)
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3"))
        player.prepare(mediaSource)
        playButton.setOnClickListener {
            with(player) {
                playWhenReady = !playWhenReady
            }
        }
    }
}