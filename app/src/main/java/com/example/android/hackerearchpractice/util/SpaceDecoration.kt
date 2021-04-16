package com.example.android.hackerearchpractice.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.State

/** ItemDecoration that adds space around items. */
class SpaceDecoration(
    private val start: Int = 0,
    private val top: Int = 0,
    private val end: Int = 0,
    private val bottom: Int = 0
) : ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        outRect.set(
            start,
            top,
            end,
            bottom
        )
    }
}