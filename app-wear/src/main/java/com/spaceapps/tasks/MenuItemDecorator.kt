package com.spaceapps.tasks

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MenuItemDecorator: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = dpToPx(16)
        val position = parent.getChildAdapterPosition(view)
        if (position == 0){
            outRect.apply {
                top = dpToPx(24)
                bottom = dpToPx(12)
            }
            return
        }else if (position == parent.childCount){
            outRect.apply {
                top = dpToPx(12)
                bottom = dpToPx(24)
            }
            return
        }
        outRect.apply {
            top = dpToPx(12)
            bottom = dpToPx(12)
        }
    }

    private fun dpToPx(px:Int):Int{
        return (Resources.getSystem().displayMetrics.density * px).toInt()
    }
}