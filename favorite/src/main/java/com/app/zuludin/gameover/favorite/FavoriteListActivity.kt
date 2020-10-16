package com.app.zuludin.gameover.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.app.zuludin.gameover.favorite.R
import kotlinx.android.synthetic.main.activity_favorite_list.*

class FavoriteListActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_list)
        configureNavigation()
        setSupportActionBar(toolbar)
        toolbar.navigationIcon =
            ContextCompat.getDrawable(applicationContext, R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun configureNavigation() {
        navController = findNavController(R.id.favorite_fragment_container)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}