package com.spaceapps.tasks

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.spaceapps.tasks.core.extensions.gone
import com.spaceapps.tasks.core.extensions.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onResume() {
        super.onResume()
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.navigation_splash -> {
                    bottomNavigationView.gone()
                }
                else -> {
                    bottomNavigationView.visible()
                }
            }
        }
    }
}