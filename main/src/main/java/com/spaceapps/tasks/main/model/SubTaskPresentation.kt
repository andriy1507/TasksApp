package com.spaceapps.tasks.main.model

import android.view.View
import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.core_ui.PresentationEntity
import com.spaceapps.tasks.core_ui.databinding.ItemSubtaskBinding
import com.spaceapps.tasks.main.R
import com.xwray.groupie.viewbinding.BindableItem

data class SubTaskPresentation(
    var text: String,
    var isDone: Boolean
) : BindableItem<ItemSubtaskBinding>(), PresentationEntity {

    override fun getLayout() = R.layout.item_subtask

    private lateinit var binding: ItemSubtaskBinding

    override fun initializeViewBinding(view: View): ItemSubtaskBinding {
        binding = ItemSubtaskBinding.bind(view)
        return binding
    }

    override fun bind(binding: ItemSubtaskBinding, position: Int) {
        binding.apply {
            subTaskCheckBox.isChecked = isDone
            subTaskEditText.setText(text)
        }
    }

    fun getSubTask() = SubTask(
        binding.subTaskEditText.text.toString(),
        binding.subTaskCheckBox.isChecked
    )
}