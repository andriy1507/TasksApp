package com.spaceapps.tasks.create.model

import android.view.View
import com.spaceapps.tasks.core_ui.databinding.ItemSubtaskBinding
import com.spaceapps.tasks.create.R
import com.xwray.groupie.viewbinding.BindableItem

class SubTaskView(
    val text: String = "",
    val isDone: Boolean = false
) : BindableItem<ItemSubtaskBinding>() {

    override fun getLayout() = R.layout.item_subtask

    override fun bind(binding: ItemSubtaskBinding, position: Int) {
        binding.subTaskCheckBox.isChecked = isDone
        binding.subTaskEditText.setText(text)
    }

    override fun initializeViewBinding(view: View) = ItemSubtaskBinding.bind(view)
}