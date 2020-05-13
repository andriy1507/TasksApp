package com.spaceapps.tasks.profile

import android.accounts.AccountManager
import com.spaceapps.tasks.core_ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    override fun setupDependencies() = Unit

    override fun onResume() {
        super.onResume()
        AccountManager.get(context).getAccountsByType(getString(R.string.account_type))
            .firstOrNull()?.let {
            accountTextView.text = it.name
        }?: run {
            ProfileFragmentDirections.navigationLogin()
        }
    }
}