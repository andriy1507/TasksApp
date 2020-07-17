package com.spaceapps.tasks.create.model

import android.view.View
import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.core_ui.databinding.ItemSubtaskBinding
import com.spaceapps.tasks.create.R
import com.xwray.groupie.viewbinding.BindableItem

class SubTaskView(
    private val item: SubTask
) : BindableItem<ItemSubtaskBinding>() {

    private lateinit var binding: ItemSubtaskBinding

    override fun getLayout() = R.layout.item_subtask

    override fun bind(binding: ItemSubtaskBinding, position: Int) {
        this.binding = binding
        binding.subTaskCheckBox.apply {
            isChecked = item.isDone
        }
        binding.subTaskEditText.apply {
            setText(this@SubTaskView.item.text)
        }
    }

    fun getSubTask() = item.copy(
        text = binding.subTaskEditText.text.toString(),
        isDone = binding.subTaskCheckBox.isChecked
    )

    override fun initializeViewBinding(view: View) = ItemSubtaskBinding.bind(view)
}