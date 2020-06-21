package com.spaceapps.tasks.main.model

import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatEditText
import com.spaceapps.tasks.core_ui.PresentationEntity
import com.spaceapps.tasks.main.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

data class SubTaskPresentation(
    val text: String,
    val isDone: Boolean
) : Item<GroupieViewHolder>(), PresentationEntity {

    override fun getLayout() = R.layout.item_subtask

    //  TODO(Enable view binding)
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            val checkBox = findViewById<AppCompatCheckBox>(R.id.subTaskCheckBox)
            val editText = findViewById<AppCompatEditText>(R.id.subTaskEditText)
            checkBox.isChecked = isDone
            editText.setText(text)
        }


    }
}