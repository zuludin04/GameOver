package com.app.zuludin.gameover.detail.views

import android.content.Intent
import android.net.Uri
import android.view.View
import com.app.zuludin.core.domain.model.GameStore
import com.app.zuludin.core.ui.GlideApp
import com.app.zuludin.core.ui.base.RecyclerAdapter
import com.app.zuludin.gameover.detail.R
import kotlinx.android.synthetic.main.item_store.view.*

class GameStoreAdapter : RecyclerAdapter<GameStore>() {
    override fun layoutId(): Int = R.layout.item_store

    override fun bindData(data: GameStore, view: View, position: Int) {
        view.store_name.text = data.name
        GlideApp.with(view.context).load(data.image).into(view.store_image)
        view.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(data.website)
            view.context.startActivity(intent)
        }
    }
}