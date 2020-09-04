package com.spaceapps.tasks.settings

import android.accounts.AccountManager
import android.os.Bundle
import android.view.View
import com.spaceapps.tasks.core.extensions.isNull
import com.spaceapps.tasks.core.extensions.navigate
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.core_ui.visibleIf
import com.spaceapps.tasks.settings.databinding.FragmentSettingsBinding
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding

class SettingsFragment : BaseFragment() {

    override val binding by lazy { FragmentSettingsBinding.inflate(layoutInflater) }

    private val notificationsSwitch by lazy { binding.notificationsSwitch }
    private val notificationsLabelTextView by lazy { binding.notificationsLabelTextView }
    private val notificationsTitleTextView by lazy { binding.notificationsTitleTextView }
    private val deleteAccountButton by lazy { binding.deleteAccountButton }
    private val accountType by lazy { getString(R.string.account_type) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initData()
        applyInsets()
        navigate(SettingsFragmentDirections.navigateAudioPlayer())
    }

    private fun applyInsets() {
        binding.root.applySystemWindowInsetsToPadding(top = true)
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
        deleteAccountButton.apply {
            val accountManager = AccountManager.get(context)
            setOnClickListener {
                if (!accountManager.getAccountsByType(accountType).firstOrNull().isNull()) {
                    AccountManager.get(context).apply {
                        val account =
                            getAccountsByType(getString(R.string.account_type)).firstOrNull()
                        removeAccountExplicitly(account)
                    }
                }
            }
            visibleIf(!accountManager.getAccountsByType(accountType).firstOrNull().isNull())
        }
    }
}