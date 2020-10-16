package com.app.zuludin.platform.list.views

import android.view.View
import androidx.navigation.findNavController
import com.app.zuludin.core.domain.model.Platform
import com.app.zuludin.core.ui.base.RecyclerAdapter
import com.app.zuludin.core.utils.extensions.gamesCount
import com.app.zuludin.core.utils.extensions.loadImageUrl
import com.app.zuludin.platform.R
import com.app.zuludin.platform.list.PlatformFragmentDirections
import kotlinx.android.synthetic.main.item_platform.view.*

class PlatformAdapter : RecyclerAdapter<Platform>() {
    override fun layoutId(): Int = R.layout.item_platform

    override fun bindData(data: Platform, view: View, position: Int) {
        view.platform_image.loadImageUrl(data.image)
        view.platform_name.text = data.name
        view.platform_game_count.gamesCount(data.count)

        view.setOnClickListener {
            it.findNavController()
                .navigate(
                    PlatformFragmentDirections.navPlatformToGames(
                        platformId = data.id!!,
                        platformTitle = data.name.toString()
                    )
                )
        }
    }
}