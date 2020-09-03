package com.spaceapps.tasks.profile

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.spaceapps.tasks.core.extensions.*
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.profile.databinding.FragmentProfileBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    companion object {
        private const val PICK_FROM_GALLERY_REQUEST_CODE = 0x1432
        private const val PERMISSION_REQUEST_CODE = 0x1254
    }

    private val viewModel: ProfileScreenViewModel by viewModels()

    @Inject
    lateinit var picasso: Picasso

    override val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }

    private val progressBar by lazy { binding.completedTasksMeasure }
    private val profileImageView by lazy { binding.profileImageImageView }
    private val loadingProgressBar by lazy { binding.loadingProgressBar }

    override fun setupDependencies() {
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
            observe(userProfile) {status ->
                status.onSuccess {
                    it?.let { picasso.loadFromBackend(it.profileImage)
                        .onCompleted { loadingProgressBar.hide() }.into(profileImageView) }
                }.onError {
                    loadingProgressBar.hide()
                    binding.root.showErrorSnackBar(R.string.some_error_occurred)
                    Timber.e(it)
                }.onLoading {
                    loadingProgressBar.show()
                }
            }
            observe(profileImageUrl) { status ->
                status.onSuccess {
                    binding.root.showSuccessSnackBar(R.string.uploaded_successfully)
                    picasso.loadFromBackend(it)
                        .onCompleted { loadingProgressBar.hide() }.into(profileImageView)
                }.onError {
                    loadingProgressBar.hide()
                    binding.root.showErrorSnackBar(R.string.some_error_occurred)
                    Timber.e(it)
                }.onLoading {
                    loadingProgressBar.show()
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
        try {
            data?.data?.let {
                val fileDescriptor = context?.contentResolver?.openFileDescriptor(it, "r")
                context?.contentResolver?.getFileName(it)?.let { fileName ->
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