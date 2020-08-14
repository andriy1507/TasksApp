package com.spaceapps.tasks.profile

import android.accounts.Account
import android.accounts.AccountManager
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.core_ui.getThemeColor
import com.spaceapps.tasks.profile.databinding.FragmentSignInBinding
import com.spaceapps.tasks.profile.di.SignInScreenComponent
import javax.inject.Inject

class SignInFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: SignInViewModel

    override val binding by lazy { FragmentSignInBinding.inflate(layoutInflater) }

    private val signInButton by lazy { binding.signInButton }
    private val loginEditText by lazy { binding.loginEditText }
    private val passwordEditText by lazy { binding.passwordEditText }

    override fun setupDependencies() {
        SignInScreenComponent.init(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        signInButton.setOnClickListener {
            viewModel.requestLogin(
                loginEditText.text.toString(),
                passwordEditText.text.toString()
            )
            if (areCredentialsValid()) {
                addAccount()
            } else {
                context?.let {
                    Snackbar.make(binding.root, "Enter valid credentials", Snackbar.LENGTH_SHORT)
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