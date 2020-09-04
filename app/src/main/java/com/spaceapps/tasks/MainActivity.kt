package com.spaceapps.tasks

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.spaceapps.tasks.core.NavigationDispatcher
import com.spaceapps.tasks.core_ui.gone
import com.spaceapps.tasks.core_ui.visible
import com.spaceapps.tasks.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val bottomNavigationView by lazy { binding.bottomNavigationView }
    private val navHostFragment by lazy { binding.navHostFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        applyEdgeToEdge()
        lifecycleScope.launchWhenResumed {
            for (destination in NavigationDispatcher.navigationActions) {
                Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment)
                    .navigate(destination)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setupNavigation()
        findNavController(R.id.nav_host_fragment)
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.navigation_splash,
                    R.id.navigation_login,
                    R.id.navigation_create,
                    R.id.navigation_audio_player -> {
                        bottomNavigationView.gone()
                    }
                    else -> {
                        bottomNavigationView.visible()
                    }
                }
            }
    }

    private fun setupNavigation() {
        NavigationUI.setupWithNavController(
            bottomNavigationView,
            navHostFragment.findNavController()
        )
    }

    @Suppress("DEPRECATION")
    private fun applyEdgeToEdge() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        }
    }
}