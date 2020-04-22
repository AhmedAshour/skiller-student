package me.ahmedashour.skiller.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import me.ahmedashour.skiller.R
import me.ahmedashour.skiller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bottomNav.setOnNavigationItemReselectedListener { menuItem ->
//            if (menuItem.itemId != binding.bottomNav.selectedItemId) {
            when (menuItem.itemId) {
                R.id.navigation_skills -> {
                }
                R.id.navigation_lessons -> {
                }
                R.id.navigation_academy -> {
                }
                R.id.navigation_more -> {
                }
            }
//            }

        }
    }
}