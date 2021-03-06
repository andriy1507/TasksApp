package com.spaceapps.tasks.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.spaceapps.tasks.core.extensions.indexInList
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core_ui.SelectableResources
import com.spaceapps.tasks.main.databinding.ItemTaskBinding
import javax.inject.Inject

class TasksAdapter @Inject constructor() : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private val items = mutableListOf<Task>()

    fun update(list: List<Task>) {
        with(items) {
            clear()
            addAll(list)
            notifyDataSetChanged()
        }
    }

    private var onTaskClickAction: ((Task?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(items[position], onTaskClickAction)
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding by lazy { ItemTaskBinding.bind(itemView) }

        fun bind(task: Task?, action: ((Task?) -> Unit)?) {
            itemView.apply {
                binding.titleTextView.text = task?.title
            }
            task?.let {
                setIcon(it)
                itemView.setOnClickListener { _ ->
                    action?.invoke(it)
                }
            }
        }

        private fun setIcon(task: Task) {
            binding.iconImageView.apply {
                task.icon?.let {
                    if (SelectableResources.ICONS.indexInList(it))
                        setImageResource(SelectableResources.ICONS[it])
                }
                task.color?.let {
                    if (SelectableResources.COLORS.indexInList(it)) {
                        ImageViewCompat
                            .setImageTintList(
                                binding.iconImageView,
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

    override fun getItemCount() = items.size
}