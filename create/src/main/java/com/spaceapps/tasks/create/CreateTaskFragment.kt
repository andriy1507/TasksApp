package com.spaceapps.tasks.create

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.spaceapps.tasks.core.extensions.indexInList
import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.core_ui.SelectableResources
import com.spaceapps.tasks.core_ui.getThemeColor
import com.spaceapps.tasks.create.databinding.FragmentCreateTaskBinding
import com.spaceapps.tasks.create.di.CreateScreenComponent
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import javax.inject.Inject

class CreateTaskFragment : BaseFragment() {

    override val binding by lazy { FragmentCreateTaskBinding.inflate(layoutInflater) }

    private val titleEditText by lazy { binding.titleEditText }
    private val colorPickerView by lazy { binding.colorPickerView }
    private val iconPickerView by lazy { binding.iconPickerView }
    private val subTasksRecyclerView by lazy { binding.subTasksRecyclerView }
    private val saveButton by lazy { binding.saveButton }
    private val addSubTaskButton by lazy { binding.addSubTaskButton }

    private var task: Task? = null

    @Inject
    lateinit var viewModel: CreateTaskViewModel

    private val subTasksAdapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CreateTaskFragmentArgs.fromBundle(requireArguments()).task
    }

    override fun setupDependencies() {
        CreateScreenComponent.Initializer().init(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTaskData()
        initClickListeners()
        initRecyclerView()
    }

    private fun initTaskData() {
        task?.let {
            titleEditText.setText(it.title)
            if (SelectableResources.COLORS.indexInList(it.color)) {
                colorPickerView.selectItem(it.color)
            }
            if (SelectableResources.ICONS.indexInList(it.icon)) {
                iconPickerView.selectItem(it.icon)
            }
//            subTasksAdapter.add(it.subTasks.map {  })
        }
    }

    private fun initRecyclerView() {
        subTasksRecyclerView.adapter = subTasksAdapter
    }

//    private fun getTask(): Task {
//        return Task(
//            titleEditText.text.toString(),
//            System.currentTimeMillis(),
//            false,
//            colorPickerView.getSelected(),
//            iconPickerView.getSelected(),
//            subTasksAdapter.getAll()
//        )
//    }

    private fun initClickListeners() {
        saveButton.setOnClickListener {
//            if (fieldsNotEmpty()) {
//                if (task == null) {
//                    viewModel.saveTask(getTask())
//                    findNavController().popBackStack()
//                } else {
//                    viewModel.updateTask(getTask())
//                    findNavController().popBackStack()
//                }
//            } else {
//                context?.let { context ->
//                    Snackbar.make(
//                        binding.root,
//                        "Please enter title and text",
//                        Snackbar.LENGTH_SHORT
//                    )
//                        .setBackgroundTint(context.getThemeColor(R.attr.colorError))
//                        .setTextColor(context.getThemeColor(R.attr.colorOnError))
//                        .show()
//                }
//            }
        }
        addSubTaskButton.setOnClickListener {
//            subTasksAdapter.addItem(SubTask("", false))
        }
    }

    private fun fieldsNotEmpty() = titleEditText.text.isNullOrBlank().not()

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    private fun hideKeyboard() {
        activity?.currentFocus?.windowToken?.let {
            (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
                ?.hideSoftInputFromWindow(it, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}