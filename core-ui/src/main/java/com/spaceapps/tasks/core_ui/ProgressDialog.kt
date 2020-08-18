package com.spaceapps.tasks.core_ui

import android.os.Bundle
import android.view.View
import com.spaceapps.tasks.core_ui.databinding.DialogProgressBinding

class ProgressDialog : BaseDialogFragment() {

    override val binding: DialogProgressBinding by lazy { DialogProgressBinding.inflate(layoutInflater) }

    private val progressBar by lazy { binding.progressBar }

    override fun setupDependencies() = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}