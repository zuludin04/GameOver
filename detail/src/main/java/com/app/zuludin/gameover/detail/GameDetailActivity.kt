package com.app.zuludin.gameover.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.core.di.DaggerCoreComponent
import com.app.zuludin.core.domain.model.GameDetail
import com.app.zuludin.core.ui.ViewModelFactory
import com.app.zuludin.core.utils.extensions.hide
import com.app.zuludin.core.utils.extensions.show
import com.app.zuludin.core.utils.extensions.showSnackbar
import com.app.zuludin.gameover.detail.databinding.ActivityGameDetailBinding
import com.app.zuludin.gameover.detail.di.DaggerDetailComponent
import com.app.zuludin.gameover.detail.views.GameGenreAdapter
import com.app.zuludin.gameover.detail.views.GameStoreAdapter
import kotlinx.android.synthetic.main.activity_game_detail.*
import javax.inject.Inject

class GameDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: GameDetailViewModel by viewModels {
        factory
    }

    private val detailArgs: GameDetailActivityArgs by navArgs()

    private var isFavorite = false
    private var itemMenu: Menu? = null

    private lateinit var gameDetailData: GameDetail
    private lateinit var binding: ActivityGameDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerDetailComponent
            .factory()
            .create(DaggerCoreComponent.factory().create(applicationContext))
            .inject(this)

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game_detail)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.detailGameWebsite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(gameDetailData.website)
            startActivity(intent)
        }

        viewModel.loadGameDetail(detailArgs.gameId)

        setupRecyclerData()
        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        itemMenu = menu
        setFavoriteIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favorite_menu) {
            viewModel.setFavoriteGame(gameDetailData, isFavorite)
            isFavorite = !isFavorite
            setFavoriteIcon()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeViewModel() {
        viewModel.isGameFavorite(detailArgs.gameId).observe(this, { favorite ->
            isFavorite = favorite
            setFavoriteIcon()
        })

        viewModel.gameDetail.observe(this, { detail ->
            gameDetailData = detail
        })

        viewModel.errorMessage.observe(this, { showSnackbar(it) })

        viewModel.loadingPage.observe(this, { loading ->
            if (loading) binding.progressBar.show()
            else binding.progressBar.hide()
        })
    }

    private fun setupRecyclerData() {
        recycler_genre.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = GameGenreAdapter()
        }

        recycler_game_store.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = GameStoreAdapter()
        }
    }

    private fun setFavoriteIcon() {
        if (itemMenu != null) {
            if (isFavorite) {
                itemMenu?.getItem(0)?.icon =
                    ContextCompat.getDrawable(applicationContext, R.drawable.ic_favorite_game)
            } else {
                itemMenu?.getItem(0)?.icon =
                    ContextCompat.getDrawable(applicationContext, R.drawable.ic_unfavorite_game)
            }
        }
    }
}