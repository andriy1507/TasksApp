package com.spaceapps.tasks.main

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.spaceapps.tasks.core.extensions.indexInList
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core_ui.BaseBottomSheetFragment
import com.spaceapps.tasks.core_ui.SelectableResources
import com.spaceapps.tasks.main.di.TaskViewScreenComponent
import kotlinx.android.synthetic.main.bottom_sheet_task_view.*
import kotlinx.android.synthetic.main.item_task.titleTextView
import javax.inject.Inject

class TaskViewBottomSheet : BaseBottomSheetFragment(R.layout.bottom_sheet_task_view) {

    @Inject
    lateinit var viewModel: TaskViewViewModel

    @Inject
    lateinit var subTasksAdapter: SubTasksAdapter

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
        }
    }

    private fun getTask(): Task? {
        return TaskViewBottomSheetArgs.fromBundle(requireArguments()).task?.copy(
            title = titleTextView.text.toString(),
            subTasks = subTasksAdapter.getItems()
        )
    }

    private fun initRecyclerView() {
        subTasksRecyclerView.apply {
            adapter = subTasksAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initTaskData() {
        TaskViewBottomSheetArgs.fromBundle(requireArguments()).task?.let {
            titleTextView.text = it.title
            taskImageView.apply {
                it.icon?.let { icon ->
                    if (SelectableResources.ICONS.indexInList(icon))
                        setImageResource(SelectableResources.ICONS[icon])
                }
                it.color?.let { color ->
                    if (SelectableResources.COLORS.indexInList(color)) {
                        ImageViewCompat
                            .setImageTintList(
                                taskImageView,
                                ContextCompat.getColorStateList(
                                    context,
                                    SelectableResources.COLORS[color]
                                )
                            )
                    }
                }
            }
            subTasksAdapter.submitList(it.subTasks)
        }
    }

}