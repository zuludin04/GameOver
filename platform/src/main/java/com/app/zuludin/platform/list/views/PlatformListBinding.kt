package com.app.zuludin.platform.list.views

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.core.domain.model.Platform

object PlatformListBinding {
    @BindingAdapter("app:platformList")
    @JvmStatic
    fun loadPlatformList(view: RecyclerView, platforms: List<Platform>?) {
        with(view.adapter as PlatformAdapter) {
            platforms?.let { addRecyclerItem(it) }
        }
    }
}