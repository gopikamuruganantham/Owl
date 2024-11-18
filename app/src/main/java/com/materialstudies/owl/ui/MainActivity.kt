

package com.materialstudies.owl.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.materialstudies.owl.R
import com.materialstudies.owl.databinding.ActivityMainBinding
import com.materialstudies.owl.util.contentView
import com.materialstudies.owl.util.hide
import com.materialstudies.owl.util.show

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by contentView(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            val navController = Navigation.findNavController(this@MainActivity, R.id.nav_host)
            bottomNav.setupWithNavController(navController)

            // Hide bottom nav on screens which don't require it
            lifecycleScope.launchWhenResumed {
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    when (destination.id) {
                        R.id.myCourses, R.id.featured, R.id.search -> bottomNav.show()
                        else -> bottomNav.hide()
                    }
                }
            }
        }
    }
}
