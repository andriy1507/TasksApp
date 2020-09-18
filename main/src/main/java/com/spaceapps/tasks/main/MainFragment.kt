package com.spaceapps.tasks.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.spaceapps.tasks.core.extensions.navigate
import com.spaceapps.tasks.core.extensions.observe
import com.spaceapps.tasks.core.extensions.viewBinding
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.main.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    @Inject
    lateinit var recyclerViewAdapter: TasksAdapter

    private val viewModel: MainScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initClickListener()
        applyInsets()
        initObserver()
        binding.swipeRefreshLayout.setOnRefreshListener {
        }
    }

    private fun applyInsets() {
        binding.tasksRecyclerView.applySystemWindowInsetsToPadding(top = true)
    }

    private fun initClickListener() {
        binding.createTaskFab.setOnClickListener {
            navigate(MainFragmentDirections.navigationCreate(null))
        }
    }

    private fun initObserver() {
        observe(viewModel.taskList) {
            recyclerViewAdapter.update(it)
        }
    }

    private fun initRecyclerView() {
        binding.tasksRecyclerView.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerViewAdapter.setOnTaskClickAction(::onTaskClick)
            recyclerViewAdapter.setOnTaskClickAction { }
        }
    }

    private fun onTaskClick(it: Task?) {
        it?.let { task ->
            navigate(MainFragmentDirections.navigationView(task.id))
        }
    }

}