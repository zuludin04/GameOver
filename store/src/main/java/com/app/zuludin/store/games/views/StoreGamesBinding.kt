package com.app.zuludin.store.games.views

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.core.domain.model.Game
import com.app.zuludin.core.ui.GameAdapter

object StoreGamesBinding {
    @BindingAdapter("app:storeGames")
    @JvmStatic
    fun loadStoreGames(view: RecyclerView, games: List<Game>?) {
        with(view.adapter as GameAdapter) {
            games?.let { addRecyclerItem(it) }
        }
    }
}