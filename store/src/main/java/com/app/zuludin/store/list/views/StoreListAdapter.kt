package com.app.zuludin.store.list.views

import android.view.View
import androidx.navigation.findNavController
import com.app.zuludin.core.domain.model.Store
import com.app.zuludin.core.ui.base.RecyclerAdapter
import com.app.zuludin.core.utils.extensions.gamesCount
import com.app.zuludin.core.utils.extensions.loadImageUrl
import com.app.zuludin.store.R
import com.app.zuludin.store.list.StoreListFragmentDirections
import kotlinx.android.synthetic.main.store_item.view.*

class StoreListAdapter : RecyclerAdapter<Store>() {
    override fun layoutId(): Int = R.layout.store_item

    override fun bindData(data: Store, view: View, position: Int) {
        view.store_image.loadImageUrl(data.image)
        view.store_name.text = data.name
        view.store_game_count.gamesCount(data.count)

        view.setOnClickListener {
            it.findNavController().navigate(
                StoreListFragmentDirections.navStoreToGames(
                    data.id!!,
                    data.name.toString()
                )
            )
        }
    }
}