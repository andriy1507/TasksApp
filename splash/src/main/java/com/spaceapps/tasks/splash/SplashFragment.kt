package com.spaceapps.tasks.splash

import android.os.Handler
import androidx.navigation.fragment.findNavController
import com.spaceapps.tasks.core_ui.BaseFragment

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    companion object {
        private const val SPLASH_SCREEN_DELAY: Long = 1500
    }

    override fun setupDependencies() = Unit

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            findNavController().navigate(SplashFragmentDirections.navigationMain())
        }, SPLASH_SCREEN_DELAY)
    }
}