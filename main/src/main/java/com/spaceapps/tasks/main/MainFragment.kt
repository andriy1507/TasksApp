package com.spaceapps.tasks.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.main.di.MainScreenComponent
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : BaseFragment(R.layout.fragment_main) {

    @Inject
    lateinit var viewModel: MainScreenViewModel

    @Inject
    lateinit var recyclerViewAdapter: TasksAdapter

    override fun setupDependencies() {
        MainScreenComponent.Initializer().init(this).inject(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.invalidateDataSource()
        initObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initClickListener()
    }

    private fun initClickListener() {
        createTaskFab.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.navigationCreate(null))
        }
    }

    private fun initObserver() {
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.submitList(it)
        })
    }

    private fun initRecyclerView() {
        tasksRecyclerView.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerViewAdapter.setOnTaskClickAction {
                findNavController().navigate(MainFragmentDirections.navigationView(it))
            }
        }

    }

}