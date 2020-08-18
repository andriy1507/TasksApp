package com.spaceapps.tasks.profile

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import com.spaceapps.tasks.core.extensions.*
import com.spaceapps.tasks.core.model.Status
import com.spaceapps.tasks.core.model.UserProfileModel
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.profile.databinding.FragmentProfileBinding
import com.spaceapps.tasks.profile.di.ProfileScreenComponent
import com.squareup.picasso.Picasso
import timber.log.Timber
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

class ProfileFragment : BaseFragment() {

    companion object {
        private const val PICK_FROM_GALLERY_REQUEST_CODE = 0x1432
        private const val PERMISSION_REQUEST_CODE = 0x1254
    }

    @Inject
    lateinit var viewModel: ProfileScreenViewModel

    @Inject
    lateinit var picasso: Picasso

    override val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }

    private val progressBar by lazy { binding.completedTasksMeasure }
    private val profileImageView by lazy { binding.profileImageImageView }
    private val loadingProgressBar by lazy { binding.loadingProgressBar }

    override fun setupDependencies() {
        ProfileScreenComponent.init(this).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            getUserProfile()
            getSubTasks()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initClickListeners()
    }

    private fun initClickListeners() {
        profileImageView.setOnClickListener {
            context?.checkPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                onGranted = { goToGallery() },
                onDenied = {
                    requestPermissions(
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        PERMISSION_REQUEST_CODE
                    )
                }
            )
        }
    }

    private fun initObservers() {
        viewModel.apply {
            observe(subTasks) { list ->
                if (!list.isNullOrEmpty()) {
                    val progress = list.count { it.isDone } / list.size * 100
                    progressBar.progress = progress
                }
            }
            observe(userProfile) {
                when (it) {
                    Status.Loading -> {
                        loadingProgressBar.show()
                    }
                    is Status.Success<*> -> {
                        (it.data as? UserProfileModel)?.let { profile ->
                            picasso.loadFromBackend(profile.profileImage)
                                .onCompleted { loadingProgressBar.hide() }.into(profileImageView)
                        }
                    }
                    is Status.Error<*> -> {
                        loadingProgressBar.hide()
                        binding.root.showErrorSnackBar(R.string.some_error_occurred)
                        Timber.e(it.error)
                    }
                }
            }
            observe(profileImageUrl) {
                when (it) {
                    Status.Loading -> loadingProgressBar.show()
                    is Status.Success<*> -> {
                        binding.root.showSuccessSnackBar(R.string.uploaded_successfully)
                        picasso.loadFromBackend((it.data as String))
                            .onCompleted { loadingProgressBar.hide() }.into(profileImageView)
                    }
                    is Status.Error<*> -> {
                        binding.root.showErrorSnackBar(R.string.some_error_occurred)
                        Timber.e(it.error)
                    }
                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PICK_FROM_GALLERY_REQUEST_CODE -> if (resultCode == RESULT_OK) parseImageFromIntent(data)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
                    goToGallery()
                }
            }
        }
    }

    private fun goToGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply { type = "image/*" }
        startActivityForResult(
            Intent.createChooser(intent, "Choose picture"),
            PICK_FROM_GALLERY_REQUEST_CODE
        )
    }

    private fun parseImageFromIntent(data: Intent?) {
        val uri = data?.data
        profileImageView.setImageURI(uri)
        try {
            uri?.let {
                val fileDescriptor = context?.contentResolver?.openFileDescriptor(uri, "r")
                context?.contentResolver?.getFileName(uri)?.let { fileName ->
                    val file = File(context?.cacheDir, fileName)
                    val inputStream = FileInputStream(fileDescriptor?.fileDescriptor)
                    val outputStream = FileOutputStream(file)
                    inputStream.copyTo(outputStream)
                    viewModel.setProfileImage(file)
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}