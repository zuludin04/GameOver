package com.app.zuludin.core.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EqualSpacingLayout(
    private val spanCount: Int = 0,
    private val spacing: Int,
    private val mode: Int = VERTICAL_MODE
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        when (mode) {
            VERTICAL_MODE -> {
                outRect.left = spacing;
                outRect.right = spacing;
                outRect.top = spacing;
                if (position == state.itemCount - 1) {
                    outRect.bottom = spacing;
                } else {
                    outRect.bottom = 0;
                }
            }
            GRID_MODE -> {
                val column = position % spanCount
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount

                if (position < spanCount) {
                    outRect.top = spacing
                }

                outRect.bottom = spacing
            }
        }
    }

    companion object {
        const val VERTICAL_MODE = 1
        const val GRID_MODE = 2
    }
}