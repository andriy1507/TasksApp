package com.spaceapps.tasks.main

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.spaceapps.tasks.core.extensions.indexInList
import com.spaceapps.tasks.core_ui.BaseBottomSheetFragment
import com.spaceapps.tasks.core_ui.SelectableResources
import com.spaceapps.tasks.main.di.TaskViewScreenComponent
import kotlinx.android.synthetic.main.bottom_sheet_task_view.*
import kotlinx.android.synthetic.main.item_task.textTextView
import kotlinx.android.synthetic.main.item_task.titleTextView
import javax.inject.Inject

class TaskViewBottomSheet : BaseBottomSheetFragment(R.layout.bottom_sheet_task_view) {

    @Inject
    lateinit var viewModel: TaskViewViewModel

    override fun setupDependencies() {
        TaskViewScreenComponent.Initializer().init(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TaskViewBottomSheetArgs.fromBundle(requireArguments()).task?.let {
            titleTextView.text = it.title
            textTextView.text = it.text
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
        }
    }

}