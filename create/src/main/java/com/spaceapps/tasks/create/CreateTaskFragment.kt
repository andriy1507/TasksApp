package com.spaceapps.tasks.create

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.spaceapps.tasks.core.extensions.indexInList
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.core_ui.SelectableResources
import com.spaceapps.tasks.core_ui.getThemeColor
import com.spaceapps.tasks.create.di.CreateScreenComponent
import kotlinx.android.synthetic.main.fragment_create_task.*
import javax.inject.Inject

class CreateTaskFragment : BaseFragment(R.layout.fragment_create_task) {

    override fun setupDependencies() {
        CreateScreenComponent.Initializer().init(this).inject(this)
    }

    private var task: Task? = null

    @Inject
    lateinit var viewModel: CreateTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CreateTaskFragmentArgs.fromBundle(requireArguments()).task
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTaskData()
        initClickListeners()
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
        }
    }

    private fun getTask(): Task {
        return Task(
            titleEditText.text.toString(),
            System.currentTimeMillis(),
            false,
            colorPickerView.getSelected(),
            iconPickerView.getSelected()
        )
    }

    private fun initClickListeners() {
        saveButton.setOnClickListener {
            if (fieldsNotEmpty()) {
                if (task == null) {
                    viewModel.saveTask(getTask())
                    findNavController().popBackStack()
                } else {
                    viewModel.updateTask(getTask())
                    findNavController().popBackStack()
                }
            } else {
                context?.let { context ->
                    Snackbar.make(root, "Please enter title and text", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(context.getThemeColor(R.attr.colorError))
                        .setTextColor(context.getThemeColor(R.attr.colorOnError))
                        .show()
                }
            }
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