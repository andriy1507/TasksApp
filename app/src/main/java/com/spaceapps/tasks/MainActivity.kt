package com.spaceapps.tasks

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.spaceapps.tasks.core_ui.gone
import com.spaceapps.tasks.core_ui.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

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
            nav_host_fragment.findNavController()
        )
    }
}