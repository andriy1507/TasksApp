package com.spaceapps.tasks.splash

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.splash.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment() {

    override val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }

    companion object {
        private const val SPLASH_SCREEN_DELAY: Long = 1500
    }

    override fun setupDependencies() = Unit

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashFragmentDirections.navigationMain())
        }, SPLASH_SCREEN_DELAY)
    }
}