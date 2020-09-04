package com.spaceapps.tasks.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.spaceapps.tasks.core.extensions.navigate
import com.spaceapps.tasks.core.extensions.observe
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.main.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    override val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val createTaskFab by lazy { binding.createTaskFab }
    private val tasksRecyclerView by lazy { binding.tasksRecyclerView }

    @Inject
    lateinit var recyclerViewAdapter: TasksAdapter

    private val viewModel: MainScreenViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initClickListener()
        applyInsets()
        initObserver()
    }

    private fun applyInsets() {
        tasksRecyclerView.applySystemWindowInsetsToPadding(top = true)
    }

    private fun initClickListener() {
        createTaskFab.setOnClickListener {
            navigate(MainFragmentDirections.navigationCreate(null))
        }
    }

    private fun initObserver() {
        observe(viewModel.taskList) {
            Timber.d(it.toString())
        }
    }

    private fun initRecyclerView() {
        tasksRecyclerView.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerViewAdapter.setOnTaskClickAction(::onTaskClick)
        }
    }

    private fun onTaskClick(it: Task?) {
        it?.let { task ->
            navigate(MainFragmentDirections.navigationView(task.id))
        }
    }

}