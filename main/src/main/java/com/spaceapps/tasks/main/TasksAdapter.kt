package com.spaceapps.tasks.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.spaceapps.tasks.core.extensions.indexInList
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core_ui.SelectableResources
import kotlinx.android.synthetic.main.item_task.view.*
import javax.inject.Inject

class TasksAdapter @Inject constructor() : PagedListAdapter<Task, TasksAdapter.TaskViewHolder>(
    object : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) =
            oldItem.timestamp == newItem.timestamp

        override fun areContentsTheSame(oldItem: Task, newItem: Task) =
            oldItem.timestamp == newItem.timestamp
    }
) {

    fun setOnTaskClickAction(action: ((Task?) -> Unit)?) {
        onTaskClickAction = action
    }

    private var onTaskClickAction: ((Task?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position), onTaskClickAction)
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task?, action: ((Task?) -> Unit)?) {
            itemView.apply {
                titleTextView.text = task?.title
            }
            task?.let {
                setIcon(it)
                itemView.setOnClickListener { _ ->
                    action?.invoke(it)
                }
            }
        }

        private fun setIcon(task: Task) {
            itemView.iconImageView.apply {
                task.icon?.let {
                    if (SelectableResources.ICONS.indexInList(it))
                        setImageResource(SelectableResources.ICONS[it])
                }
                task.color?.let {
                    if (SelectableResources.COLORS.indexInList(it)) {
                        ImageViewCompat
                            .setImageTintList(
                                itemView.iconImageView,
                                ContextCompat.getColorStateList(
                                    context,
                                    SelectableResources.COLORS[it]
                                )
                            )
                    }
                }

            }
        }
    }
}