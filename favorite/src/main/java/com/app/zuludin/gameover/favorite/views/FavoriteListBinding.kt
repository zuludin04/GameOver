package com.app.zuludin.gameover.favorite.views

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.core.domain.model.Game
import com.app.zuludin.core.ui.GameAdapter
import com.app.zuludin.core.utils.extensions.hide
import com.app.zuludin.core.utils.extensions.show

object FavoriteListBinding {

    @BindingAdapter("app:favorites")
    @JvmStatic
    fun loadFavoriteList(view: RecyclerView, favorites: List<Game>?) {
        with(view.adapter as GameAdapter) {
            favorites?.let { addRecyclerItem(it) }
        }
    }

    @BindingAdapter("app:showEmptyLayout")
    @JvmStatic
    fun showEmptyLayout(view: View, isEmpty: Boolean?) {
        if (isEmpty != null) {
            if (isEmpty) view.show()
            else view.hide()
        }
    }
}