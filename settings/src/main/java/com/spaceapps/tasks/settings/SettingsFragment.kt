package com.spaceapps.tasks.settings

import android.accounts.AccountManager
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.spaceapps.tasks.core_ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun setupDependencies() = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initData()
    }

    private fun initData() {
        notificationsSwitch.isChecked = true
    }

    private fun initListeners() {
        notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            context?.let {
                notificationsLabelTextView.text = getString(
                    R.string.notifications_state,
                    if (isChecked) it.getString(R.string.enabled) else it.getString(R.string.disabled)
                )
            }
        }
        deleteAccountButton.setOnClickListener {
            AccountManager.get(context).apply {
                val account = getAccountsByType(getString(R.string.account_type)).firstOrNull()
                removeAccountExplicitly(account)
            }
        }
    }
}