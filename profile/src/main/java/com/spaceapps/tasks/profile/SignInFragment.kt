package com.spaceapps.tasks.profile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.spaceapps.tasks.core.extensions.*
import com.spaceapps.tasks.core_ui.gone
import com.spaceapps.tasks.core_ui.visible
import com.spaceapps.tasks.profile.SignInFragmentDirections.Companion.navigationUserProfile
import com.spaceapps.tasks.profile.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val viewModel: SignInViewModel by viewModels()

    private val binding by viewBinding(FragmentSignInBinding::bind)

    private val header by lazy { binding.header }
    private val signInButton by lazy { binding.signInButton }
    private val loginEditText by lazy { binding.loginEditText }
    private val passwordEditText by lazy { binding.passwordEditText }
    private val loadingProgressBar by lazy { binding.loadingProgressBar }
    private val noteTextView by lazy { binding.noteTextView }
    private val buttonTextView by lazy { binding.buttonTextView }
    private val googleSignInButton by lazy { binding.googleSignInButton }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initClickListeners()
    }

    private fun initClickListeners() {
        buttonTextView.setOnClickListener { viewModel.toggleState() }
        googleSignInButton.setOnClickListener {
            val options =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
                    .build()
            val client = GoogleSignIn.getClient(requireContext(), options)
            startActivityForResult(client.signInIntent, GOOGLE_SING_IN_RC)
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
            observe(authorized) { status ->
                status.onSuccess {
                    loadingProgressBar.gone()
                    if (it) {
                        binding.root.showSuccessSnackBar(R.string.successfully_logged_in)
                        Handler(Looper.getMainLooper()).postDelayed({
                            navigate(navigationUserProfile())
                        }, LOG_IN_THRESHOLD)
                    }
                }.onLoading {
                    loadingProgressBar.visible()
                }.onError {
                    loadingProgressBar.gone()
                    binding.root.showErrorSnackBar(R.string.some_error_occurred)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GOOGLE_SING_IN_RC -> {
                GoogleSignIn.getSignedInAccountFromIntent(data)?.addOnSuccessListener {
                    binding.root.showSuccessSnackBar(R.string.successfully_logged_in)
                }?.addOnFailureListener {
                    binding.root.showErrorSnackBar(R.string.some_error_occurred)
                    Timber.e(it)
                }
            }
        }
    }

    companion object {
        private const val LOG_IN_THRESHOLD: Long = 1500
        private const val GOOGLE_SING_IN_RC = 0x1234
    }
}