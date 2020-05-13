package com.spaceapps.tasks.core_ui.view.icon_picker

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.spaceapps.tasks.core_ui.R
import kotlinx.android.synthetic.main.component_picker.view.*

class IconPickerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val iconAdapter by lazy { IconPickerAdapter() }

    init {
        View.inflate(context, R.layout.component_picker, this)
        initRecycler()
    }

    fun selectItem(position: Int?) {
        position?.let {
            iconAdapter.selectItem(position)
        }
    }

    fun getSelected() = iconAdapter.getSelected()

    private fun initRecycler() {
        recyclerView.apply {
            adapter = iconAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

}