package com.app.zuludin.store.list.views

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.core.domain.model.Store

object StoreListBinding {
    @BindingAdapter("app:storeList")
    @JvmStatic
    fun loadStoreList(view: RecyclerView, stores: List<Store>?) {
        with(view.adapter as StoreListAdapter) {
            stores?.let { addRecyclerItem(it) }
        }
    }
}