package com.app.zuludin.platform.games

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.app.zuludin.platform.R
import kotlinx.android.synthetic.main.activity_platform_games.*

class PlatformGamesActivity : AppCompatActivity() {

    private val platformArgs by navArgs<PlatformGamesActivityArgs>()

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_platform_games)
        configureNavigation()
        setSupportActionBar(toolbar)
        supportActionBar?.title = platformArgs.platformTitle
        toolbar.navigationIcon =
            ContextCompat.getDrawable(applicationContext, R.drawable.ic_back_button)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) ||  super.onSupportNavigateUp()
    }

    private fun configureNavigation() {
        navController = findNavController(R.id.platform_fragment_container)
        navController.setGraph(R.navigation.nav_graph_platform, intent.extras)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}