package com.spaceapps.tasks.splash

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.spaceapps.tasks.core.extensions.viewBinding
import com.spaceapps.tasks.splash.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding by viewBinding(FragmentSplashBinding::bind)

    companion object {
        private const val SPLASH_SCREEN_DELAY: Long = 1500
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashFragmentDirections.navigationMain())
        }, SPLASH_SCREEN_DELAY)
    }
}