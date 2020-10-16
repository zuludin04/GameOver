package com.app.zuludin.platform.games.views

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.core.domain.model.Game
import com.app.zuludin.core.ui.GameAdapter

object PlatformGamesBinding {
    @BindingAdapter("app:platformGames")
    @JvmStatic
    fun loadPlatformGames(view: RecyclerView, games: List<Game>?) {
        with(view.adapter as GameAdapter) {
            games?.let { addRecyclerItem(it) }
        }
    }
}