package com.spaceapps.tasks.core_ui.view.color_picker

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.spaceapps.tasks.core_ui.R
import com.spaceapps.tasks.core_ui.databinding.ComponentPickerBinding

class ColorPickerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val colorsAdapter by lazy { ColorPickerAdapter() }
    private val binding: ComponentPickerBinding

    init {
        val view = View.inflate(context, R.layout.component_picker, this)
        binding = ComponentPickerBinding.bind(view)
        initRecyclerView()
    }

    @get:ColorRes
    val selected
        get() = colorsAdapter.getSelected()

    fun selectItem(position: Int?){
        position?.let {
            colorsAdapter.selectItem(position)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = colorsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}