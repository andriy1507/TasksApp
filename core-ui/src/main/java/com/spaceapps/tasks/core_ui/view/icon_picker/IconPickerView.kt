package com.spaceapps.tasks.core_ui.view.icon_picker

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.spaceapps.tasks.core_ui.R
import com.spaceapps.tasks.core_ui.databinding.ComponentPickerBinding

class IconPickerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val iconAdapter by lazy { IconPickerAdapter() }
    private val binding: ComponentPickerBinding

    init {
        val view = View.inflate(context, R.layout.component_picker, this)
        binding = ComponentPickerBinding.bind(view)
        initRecycler()
    }

    fun selectItem(position: Int?) {
        position?.let {
            iconAdapter.selectItem(position)
        }
    }

    @get:DrawableRes
    val selected
        get() = iconAdapter.getSelected()

    private fun initRecycler() {
        binding.recyclerView.apply {
            adapter = iconAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

}