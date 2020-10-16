package com.app.zuludin.home.views

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.core.domain.model.Game
import com.app.zuludin.core.ui.GameAdapter

object GameBinding {
    @BindingAdapter("app:games")
    @JvmStatic
    fun loadGames(view: RecyclerView, games: List<Game>?) {
        with(view.adapter as GameAdapter) {
            games?.let { addRecyclerItem(it) }
        }
    }
}