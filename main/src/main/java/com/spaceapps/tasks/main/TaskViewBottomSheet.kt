package com.spaceapps.tasks.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.spaceapps.tasks.core.extensions.indexInList
import com.spaceapps.tasks.core.extensions.observe
import com.spaceapps.tasks.core.extensions.observeNullable
import com.spaceapps.tasks.core.extensions.onSuccess
import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.core_ui.BaseBottomSheetFragment
import com.spaceapps.tasks.core_ui.SelectableResources
import com.spaceapps.tasks.core_ui.setIconColor
import com.spaceapps.tasks.main.databinding.BottomSheetTaskViewBinding
import com.spaceapps.tasks.main.model.SubTaskPresentation
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskViewBottomSheet : BaseBottomSheetFragment() {

    override val binding by lazy { BottomSheetTaskViewBinding.inflate(layoutInflater) }

    private val subTasksRecyclerView by lazy { binding.subTasksRecyclerView }
    private val saveButton by lazy { binding.saveButton }
    private val titleTextView by lazy { binding.titleTextView }
    private val taskImageView by lazy { binding.taskImageView }

    private val viewModel: TaskViewViewModel by viewModels()

    private val subTasksAdapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun setupDependencies() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initTaskData()
        initOnClickListener()
    }

    private fun initOnClickListener() {
        saveButton.setOnClickListener {
            getTask()?.let { task -> viewModel.updateTask(task) }
            dismiss()
        }
    }

    private fun getTask() = viewModel.task.value?.copy(
        title = titleTextView.text.toString(),
        subTasks = getSubTasks()
    )

    private fun getSubTasks(): List<SubTask> = mutableListOf<SubTask>().apply {
        for (i in 0 until subTasksAdapter.itemCount) {
            val item = subTasksAdapter.getItem(i) as SubTaskPresentation
            add(item.getSubTask())
        }
    }

    private fun initRecyclerView() {
        subTasksRecyclerView.adapter = subTasksAdapter
    }

    private fun initTaskData() {
        viewModel.getTask(TaskViewBottomSheetArgs.fromBundle(requireArguments()).taskId)
        observeNullable(viewModel.task) {task ->

                titleTextView.text = task.title
                taskImageView.apply {
                    task.icon?.let { icon ->
                        if (SelectableResources.ICONS.indexInList(icon))
                            setImageResource(SelectableResources.ICONS[icon])
                    }
                    task.color?.let { color ->
                        if (SelectableResources.COLORS.indexInList(color)) {
                            setIconColor(SelectableResources.COLORS[color])
                        }
                    }

                subTasksAdapter.apply {
                    clear()
                    addAll(task.subTasks.map { subTask ->  SubTaskPresentation(subTask) }.orEmpty())
                }
            }

        }
    }

}

