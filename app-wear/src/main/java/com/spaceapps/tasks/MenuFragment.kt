package com.spaceapps.tasks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.wear.widget.WearableLinearLayoutManager
import com.spaceapps.tasks.core.extensions.viewBinding
import com.spaceapps.tasks.databinding.FragmentMenuBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val viewModel by viewModels<MenuScreenViewModel>()
    private val binding by viewBinding(FragmentMenuBinding::bind)
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.tasks.observe(viewLifecycleOwner){
            println(it.toString())
        }
        with(binding) {
            recyclerView.apply {
                layoutManager = WearableLinearLayoutManager(requireContext())
                adapter = this@MenuFragment.adapter
                LinearSnapHelper().attachToRecyclerView(this)
                addItemDecoration(MenuItemDecorator())
            }
            val list = listOf(
                MenuItem(R.drawable.ic_launcher_foreground, R.string.create_task),
                MenuItem(R.drawable.ic_launcher_foreground, R.string.all_tasks)
            )
            adapter.addAll(list)
            adapter.setOnItemClickListener { _, view ->
                when (recyclerView.getChildAdapterPosition(view)) {
                    0 -> {
                        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                            putExtra(
                                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                            )
                        }
                        startActivityForResult(intent, VOICE_RECOGNITION_RC)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            VOICE_RECOGNITION_RC -> {
                if (resultCode == Activity.RESULT_OK) {
                    val result =
                        data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()
                    viewModel.saveTask(result)
                    Log.e(javaClass.simpleName, result.toString())
                }
            }
        }
    }

    companion object {
        private const val VOICE_RECOGNITION_RC = 0x5421
    }

}