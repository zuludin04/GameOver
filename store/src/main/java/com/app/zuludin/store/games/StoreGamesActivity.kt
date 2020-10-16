package com.app.zuludin.store.games

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import androidx.navigation.ui.*
import com.app.zuludin.store.R
import kotlinx.android.synthetic.main.activity_store_games.*

class StoreGamesActivity : AppCompatActivity() {

    private val storeGamesArgs by navArgs<StoreGamesActivityArgs>()

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_games)
        configureNavigation()
        setSupportActionBar(toolbar)
        supportActionBar?.title = storeGamesArgs.storeTitle
        toolbar.navigationIcon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_back_button)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun configureNavigation() {
        navController = findNavController(R.id.store_fragment_container)
        navController.setGraph(R.navigation.nav_graph_store, intent.extras)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}