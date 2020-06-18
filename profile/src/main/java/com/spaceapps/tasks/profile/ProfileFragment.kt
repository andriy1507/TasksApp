package com.spaceapps.tasks.profile

import android.accounts.AccountManager
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.profile.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment() {

    override val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }

    private val accountTextView by lazy { binding.accountTextView }

    override fun setupDependencies() = Unit

    override fun onResume() {
        super.onResume()
        AccountManager.get(context).getAccountsByType(getString(R.string.account_type))
            .firstOrNull()?.let {
                accountTextView.text = it.name
            } ?: run {
            ProfileFragmentDirections.navigationLogin()
        }
    }
}