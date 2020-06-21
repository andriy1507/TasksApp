package com.spaceapps.tasks.create.model

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.core_ui.databinding.ItemSubtaskBinding
import com.spaceapps.tasks.create.R
import com.xwray.groupie.viewbinding.BindableItem

class SubTaskView(
    var text: String = "",
    var isDone: Boolean = false
) : BindableItem<ItemSubtaskBinding>() {

    override fun getLayout() = R.layout.item_subtask

    override fun bind(binding: ItemSubtaskBinding, position: Int) {
        binding.subTaskCheckBox.apply {
            isChecked = isDone
            setOnCheckedChangeListener { _, isChecked ->
                isDone = isChecked
            }
        }
        binding.subTaskEditText.apply {
            setText(this@SubTaskView.text)
            addTextChangedListener(object :TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    this@SubTaskView.text = s.toString()
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            })
        }
    }

    fun getSubTask() = SubTask(text, isDone)

    override fun initializeViewBinding(view: View) = ItemSubtaskBinding.bind(view)
}