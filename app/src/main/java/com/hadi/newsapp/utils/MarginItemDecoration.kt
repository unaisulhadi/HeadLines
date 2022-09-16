package com.hadi.newsapp.utils

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(@DimenRes val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val spaceSize = view.context.resources.getDimensionPixelSize(margin)

        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceSize
            }
            left = spaceSize
            right = spaceSize
            bottom = spaceSize
        }
    }
}