package com.spaceapps.tasks.profile

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.spaceapps.tasks.core.extensions.navigate
import com.spaceapps.tasks.core.extensions.observe
import com.spaceapps.tasks.core.model.Status
import com.spaceapps.tasks.core_ui.BaseFragment
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
        buttonTextView.setOnClickListener {
            viewModel.toggleState()
        }
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
            observe(authorized) {
                when (it) {
                    is Status.Success<*> -> {
                        loadingProgressBar.hide()
                        Snackbar.make(binding.root, "Successfully logged in", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.GREEN).show()
                        Handler().postDelayed({
                            navigate(navigationUserProfile())
                        }, LOG_IN_THRESHOLD)
                    }
                    is Status.Error<out Throwable> -> {
                        loadingProgressBar.hide()
                        Snackbar.make(binding.root, "Error occurred", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.RED).show()
                    }
                    is Status.Loading -> {
                        loadingProgressBar.show()
                    }
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
    }

    companion object {
        private const val LOG_IN_THRESHOLD: Long = 1500
    }
}