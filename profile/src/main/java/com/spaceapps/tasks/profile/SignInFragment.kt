package com.spaceapps.tasks.profile

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.spaceapps.tasks.core.extensions.*
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.core_ui.gone
import com.spaceapps.tasks.core_ui.visible
import com.spaceapps.tasks.profile.SignInFragmentDirections.Companion.navigationUserProfile
import com.spaceapps.tasks.profile.databinding.FragmentSignInBinding
import com.spaceapps.tasks.profile.di.SignInScreenComponent
import javax.inject.Inject

class SignInFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: SignInViewModel

    override val binding by lazy { FragmentSignInBinding.inflate(layoutInflater) }

    private val header by lazy { binding.header }
    private val signInButton by lazy { binding.signInButton }
    private val loginEditText by lazy { binding.loginEditText }
    private val passwordEditText by lazy { binding.passwordEditText }
    private val loadingProgressBar by lazy { binding.loadingProgressBar }
    private val noteTextView by lazy { binding.noteTextView }
    private val buttonTextView by lazy { binding.buttonTextView }

    override fun setupDependencies() {
        SignInScreenComponent.init(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initClickListeners()
    }

    private fun initClickListeners() {
        buttonTextView.setOnClickListener { viewModel.toggleState() }
    }

    private fun initObservers() {
        viewModel.apply {
            observe(exists) {
                if (it) navigate(navigationUserProfile())
            }
            observe(state) {
                when (it) {
                    SignInViewModel.State.SIGN_IN -> {
                        setSignInState()
                    }
                    SignInViewModel.State.SIGN_UP -> {
                        setSignUpState()
                    }
                }
            }
            observe(authorized) { status ->
                status.onSuccess {
                    loadingProgressBar.gone()
                    if (it) {
                        binding.root.showSuccessSnackBar(R.string.successfully_logged_in)
                        Handler(Looper.getMainLooper()).postDelayed({
                            navigate(navigationUserProfile())
                        }, LOG_IN_THRESHOLD)
                    }
                }.onError {
                    loadingProgressBar.gone()
                    binding.root.showErrorSnackBar(R.string.some_error_occurred)
                }.onLoading {
                    loadingProgressBar.visible()
                }
            }
        }
    }

    private fun setSignInState() {
        header.setText(R.string.sign_in)
        signInButton.setOnClickListener {
            viewModel.requestLogin(
                loginEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
        noteTextView.setText(R.string.dont_have_an_account)
        buttonTextView.setText(R.string.sign_up)
        signInButton.setText(R.string.sign_in)
    }

    private fun setSignUpState() {
        header.setText(R.string.sign_up)
        signInButton.setOnClickListener {
            viewModel.requestRegistration(
                loginEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
        noteTextView.setText(R.string.already_have_an_account)
        buttonTextView.setText(R.string.sign_in)
        signInButton.setText(R.string.sign_up)
    }

    companion object {
        private const val LOG_IN_THRESHOLD: Long = 1500
    }
}