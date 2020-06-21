package com.spaceapps.tasks.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.main.databinding.FragmentMainBinding
import com.spaceapps.tasks.main.di.MainScreenComponent
import javax.inject.Inject

class MainFragment : BaseFragment() {

    override val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val createTaskFab by lazy { binding.createTaskFab }
    private val tasksRecyclerView by lazy { binding.tasksRecyclerView }

    @Inject
    lateinit var viewModel: MainScreenViewModel

    @Inject
    lateinit var recyclerViewAdapter: TasksAdapter

    override fun setupDependencies() {
        MainScreenComponent.Initializer().init(this).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
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