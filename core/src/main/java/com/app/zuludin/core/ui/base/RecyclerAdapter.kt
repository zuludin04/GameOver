package com.app.zuludin.core.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerAdapter<D> : RecyclerView.Adapter<RecyclerAdapter<D>.RecyclerViewHolder>() {

    private val itemList: ArrayList<D> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder =
        RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(layoutId(), parent, false))

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(itemList[position], position)
    }

    override fun getItemCount(): Int = itemList.size

    abstract fun layoutId(): Int

    abstract fun bindData(data: D, view: View, position: Int)

    fun addRecyclerItem(items: List<D>?) {
        if (items == null) return
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: D, position: Int) {
            bindData(data, itemView, position)
        }
    }
}