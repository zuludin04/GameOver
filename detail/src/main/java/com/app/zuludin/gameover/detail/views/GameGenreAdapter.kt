package com.app.zuludin.gameover.detail.views

import android.view.View
import com.app.zuludin.core.ui.base.RecyclerAdapter
import com.app.zuludin.gameover.detail.R
import kotlinx.android.synthetic.main.item_genre.view.*

class GameGenreAdapter : RecyclerAdapter<String>() {
    override fun layoutId(): Int = R.layout.item_genre

    override fun bindData(data: String, view: View, position: Int) {
        view.genre_name.text = data
    }
}