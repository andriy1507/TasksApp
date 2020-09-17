package com.spaceapps.tasks

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.spaceapps.tasks.core.extensions.viewBinding
import com.spaceapps.tasks.databinding.FragmentSplashWearableBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashWearableFragment : Fragment(R.layout.fragment_splash_wearable) {

    private val binding by viewBinding(FragmentSplashWearableBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashWearableFragmentDirections.goFragmentTwo())
        }, 1500)
    }

}