package com.spaceapps.tasks.main

import com.spaceapps.tasks.core.model.SubTask
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import javax.inject.Inject

class SubTasksAdapter @Inject constructor() :
    GroupAdapter<GroupieViewHolder>() {

    private val items: MutableList<SubTask> = mutableListOf()

    fun getItems() = items.toList()

    override fun getItemCount() = items.size

    fun addItem(item: SubTask) {
        items.add(item)
        notifyItemInserted(itemCount - 1)
    }


    fun submitList(newItems: List<SubTask>) {
        items.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }
}