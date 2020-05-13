package com.spaceapps.tasks.profile

import android.accounts.Account
import android.accounts.AccountManager
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.core_ui.getThemeColor
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    override fun setupDependencies() = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        signInButton.setOnClickListener {
            if (areCredentialsValid()) {
                addAccount()
            } else {
                context?.let {
                    Snackbar.make(root, "Enter valid credentials", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(it.getThemeColor(R.attr.colorError))
                        .setTextColor(it.getThemeColor(R.attr.colorOnError)).show()
                }
            }
        }
    }

    private fun areCredentialsValid(): Boolean {
        val login = loginEditText.text
        val password = passwordEditText.text
        return !login.isNullOrBlank() && Patterns.EMAIL_ADDRESS.matcher(login).matches()
                && !password.isNullOrBlank() && password.length >= 8
    }

    private fun addAccount() {
        AccountManager.get(context).addAccountExplicitly(
            Account(loginEditText.text.toString(), getString(R.string.account_type)),
            passwordEditText.text.toString(),
            null
        )
        findNavController().popBackStack(R.id.navigation_profile, false)
    }
}