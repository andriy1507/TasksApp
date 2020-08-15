package com.spaceapps.tasks.profile

import android.accounts.Account
import android.accounts.AccountManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.spaceapps.tasks.core.extensions.loadFromBackend
import com.spaceapps.tasks.core.extensions.observe
import com.spaceapps.tasks.core.model.Status
import com.spaceapps.tasks.core.model.UserProfileModel
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.profile.databinding.FragmentProfileBinding
import com.spaceapps.tasks.profile.di.ProfileScreenComponent
import com.squareup.picasso.Picasso
import com.squareup.picasso.PicassoProvider
import timber.log.Timber
import javax.inject.Inject

class ProfileFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: ProfileScreenViewModel

    @Inject
    lateinit var picasso: Picasso

    override val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }

    private val progressBar by lazy { binding.completedTasksMeasure }
    private val profileImageView by lazy { binding.profileImageImageView }

    override fun setupDependencies() {
        ProfileScreenComponent.init(this).inject(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.apply {
            getSubTasks()
            getUserProfile()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.apply {
            observe(subTasks) { list ->
                if (!list.isNullOrEmpty()) {
                    val progress = list.count { it.isDone } / list.size * 100
                    progressBar.progress = progress
                }
            }
            userProfile.observe(viewLifecycleOwner, Observer {
                when (it) {
                    Status.Loading -> {
                        Toast.makeText(context, "Loading ...", Toast.LENGTH_SHORT).show()
                    }
                    is Status.Success<*> -> {
                        (it.data as? UserProfileModel)?.let {profile ->
                            picasso.loadFromBackend(profile.profileImage).into(profileImageView)
                        }
                    }
                    is Status.Error<*> -> {
                        Toast.makeText(context, it.error.localizedMessage, Toast.LENGTH_SHORT).show()
                        Timber.tag("ERROR").e(it.error)
                    }
                }
            })
        }

    }

    private fun ifAccountExists(block: (Account) -> Unit) {
        AccountManager.get(context).getAccountsByType(getString(R.string.account_type))
            .firstOrNull()?.let { block(it) } ?: run {
            ProfileFragmentDirections.navigationLogin()
        }
    }
}