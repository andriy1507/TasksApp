package com.spaceapps.tasks.create.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.create.R
import kotlinx.android.synthetic.main.item_subtask.view.*
import javax.inject.Inject

class SubTasksAdapter @Inject constructor() :
    RecyclerView.Adapter<SubTasksAdapter.SubTaskViewHolder>() {

    private val items: MutableList<SubTask> = mutableListOf()

    private fun getItem(p: Int) = items[p]

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

    class SubTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(subTask: SubTask?) {
            itemView.apply {
                subTaskEditText.setText(subTask?.text.orEmpty())
                subTaskCheckBox.isChecked = subTask?.isDone ?: false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subtask, parent, false)
        return SubTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubTaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}