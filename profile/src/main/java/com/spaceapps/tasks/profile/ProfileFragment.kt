package com.spaceapps.tasks.profile

import android.accounts.Account
import android.accounts.AccountManager
import android.os.Bundle
import android.view.View
import com.spaceapps.tasks.core.extensions.observe
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.profile.databinding.FragmentProfileBinding
import com.spaceapps.tasks.profile.di.ProfileScreenComponent
import javax.inject.Inject

class ProfileFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: ProfileScreenViewModel

    override val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }
    private val profileImageImageView by lazy { binding.profileImageImageView }

    private val progressBar by lazy { binding.completedTasksMeasure }

    override fun setupDependencies() {
        ProfileScreenComponent.init(this).inject(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSubTasks()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        observe(viewModel.subTasks) { list ->
            val progress = list.count { it.isDone } / list.size * 100
            progressBar.progress = progress
        }
    }

    private fun ifAccountExists(block: (Account) -> Unit) {
        AccountManager.get(context).getAccountsByType(getString(R.string.account_type))
            .firstOrNull()?.let { block(it) } ?: run {
            ProfileFragmentDirections.navigationLogin()
        }
    }
}