package com.spaceapps.tasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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
    }

    override fun onResume() {
        super.onResume()
        setupNavigation()
        findNavController(R.id.nav_host_fragment)
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.navigation_splash,
                    R.id.navigation_login,
                    R.id.navigation_create -> {
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
}