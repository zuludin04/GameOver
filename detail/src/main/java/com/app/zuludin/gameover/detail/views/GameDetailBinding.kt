package com.app.zuludin.gameover.detail.views

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.core.domain.model.GameStore
import com.app.zuludin.core.ui.GlideApp
import com.app.zuludin.core.utils.extensions.parseDate
import com.app.zuludin.core.utils.extensions.showHtmlText
import com.app.zuludin.gameover.detail.R
import com.devs.readmoreoption.ReadMoreOption

object GameDetailBinding {
    @BindingAdapter("app:loadDetailImage")
    @JvmStatic
    fun loadDetailImage(view: ImageView, url: String?) {
        GlideApp.with(view.context).load(url).into(view)
    }

    @BindingAdapter("app:gameDescription")
    @JvmStatic
    fun gameDescription(view: TextView, description: String?) {
        val readMoreOption = ReadMoreOption.Builder(view.context)
            .textLength(5, ReadMoreOption.TYPE_LINE)
            .moreLabel("Read More")
            .lessLabel("Less")
            .moreLabelColor(ContextCompat.getColor(view.context, R.color.colorPrimary))
            .lessLabelColor(ContextCompat.getColor(view.context, R.color.colorPrimary))
            .expandAnimation(true)
            .build()

        readMoreOption.addReadMoreTo(view, description?.showHtmlText())
    }

    @BindingAdapter("app:genres")
    @JvmStatic
    fun loadGameGenre(view: RecyclerView, genres: List<String>?) {
        with(view.adapter as GameGenreAdapter) {
            genres?.let { addRecyclerItem(it) }
        }
    }

    @BindingAdapter("app:store")
    @JvmStatic
    fun loadGameStore(view: RecyclerView, stores: List<GameStore>?) {
        with(view.adapter as GameStoreAdapter) {
            stores?.let { addRecyclerItem(it) }
        }
    }

    @BindingAdapter("app:parseDate")
    @JvmStatic
    fun parseReleaseDate(view: TextView, date: String?) {
        view.text = date?.parseDate()
    }

    @BindingAdapter("app:removeListBracket")
    @JvmStatic
    fun removeListBracket(view: TextView, text: String?) {
        text?.let {
            view.text = it.removePrefix("[").removeSuffix("]")
        }
    }
}