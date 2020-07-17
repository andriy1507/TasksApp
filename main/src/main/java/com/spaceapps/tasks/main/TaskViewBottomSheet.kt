package com.spaceapps.tasks.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.spaceapps.tasks.core.extensions.indexInList
import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core_ui.BaseBottomSheetFragment
import com.spaceapps.tasks.core_ui.SelectableResources
import com.spaceapps.tasks.core_ui.setIconColor
import com.spaceapps.tasks.main.databinding.BottomSheetTaskViewBinding
import com.spaceapps.tasks.main.di.TaskViewScreenComponent
import com.spaceapps.tasks.main.model.SubTaskPresentation
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import javax.inject.Inject

class TaskViewBottomSheet : BaseBottomSheetFragment() {

    override val binding by lazy { BottomSheetTaskViewBinding.inflate(layoutInflater) }

    private val subTasksRecyclerView by lazy { binding.subTasksRecyclerView }
    private val saveButton by lazy { binding.saveButton }
    private val titleTextView by lazy { binding.titleTextView }
    private val taskImageView by lazy { binding.taskImageView }

    @Inject
    lateinit var viewModel: TaskViewViewModel

    private val subTasksAdapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun setupDependencies() {
        TaskViewScreenComponent.Initializer().init(this).inject(this)
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

    private fun getTask(): Task? {
        return viewModel.task.value?.copy(
            title = titleTextView.text.toString(),
            subTasks = getSubTasks()
        )
    }

    private fun getSubTasks(): List<SubTask> {
        val list = mutableListOf<SubTask>()
        for (i in 0 until subTasksAdapter.itemCount) {
            val item = subTasksAdapter.getItem(i) as SubTaskPresentation
            list.add(item.getSubTask())
        }
        return list
    }

    private fun initRecyclerView() {
        subTasksRecyclerView.adapter = subTasksAdapter
    }

    private fun initTaskData() {
        viewModel.getTask(TaskViewBottomSheetArgs.fromBundle(requireArguments()).taskId)
        viewModel.task.observe(viewLifecycleOwner, Observer { task ->
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
            }
            subTasksAdapter.apply {
                clear()
                addAll(task.subTasks.map { SubTaskPresentation(it.text, it.isDone) })
            }
        })
    }

}