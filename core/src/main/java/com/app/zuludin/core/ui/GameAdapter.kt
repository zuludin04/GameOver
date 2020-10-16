package com.app.zuludin.core.ui

import android.view.View
import com.app.zuludin.core.R
import com.app.zuludin.core.domain.model.Game
import com.app.zuludin.core.ui.base.RecyclerAdapter
import com.app.zuludin.core.utils.extensions.loadImageUrl
import com.app.zuludin.core.utils.extensions.parseDate
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val showDetail: (View, Int) -> Unit) : RecyclerAdapter<Game>() {
    override fun layoutId(): Int = R.layout.item_game

    override fun bindData(data: Game, view: View, position: Int) {
        view.game_icon.loadImageUrl(data.backgroundImage)
        view.game_name.text = data.name
        view.game_rating.text = data.rating.toString()
        view.game_release.text = data.released?.parseDate()

        view.setOnClickListener {
            data.id?.let { id ->
                showDetail(it, id)
            }
        }
    }
}