package com.spaceapps.tasks.core.extensions

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import com.spaceapps.tasks.core.R

@JvmOverloads
fun View.showSuccessSnackBar(
    @StringRes text: Int, duration: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(this, text, duration).apply {
        view.setBackgroundColor(context.getColor(R.color.snackbar_success))
        setActionTextColor(context.getColor(R.color.snackbar_on_success))
    }.show()
}

@JvmOverloads
fun View.showErrorSnackBar(
    @StringRes text: Int, duration: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(this, text, duration).apply {
        view.setBackgroundColor(context.getColor(R.color.snackbar_error))
        setActionTextColor(context.getColor(R.color.snackbar_on_error))
    }.show()
}