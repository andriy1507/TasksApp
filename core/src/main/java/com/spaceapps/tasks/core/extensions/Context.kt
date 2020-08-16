package com.spaceapps.tasks.core.extensions

import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.OpenableColumns
import androidx.core.content.ContextCompat

fun Context.checkPermissions(
    permission: String,
    onGranted: (() -> Unit)? = null,
    onDenied: (() -> Unit)? = null
): Boolean {
    return if (ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        onGranted?.invoke()
        true
    } else {
        onDenied?.invoke()
        false
    }
}

fun ContentResolver.getFileName(uri: Uri): String? {
    val cursor = query(uri, null, null, null, null)
    return cursor?.use {
        it.moveToFirst()
        cursor.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    }
}