package com.spaceapps.tasks.core.extensions

import com.spaceapps.tasks.core.BuildConfig
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import kotlin.Exception

fun Picasso.loadFromBackend(url: String?): RequestCreator {
    return load(BuildConfig.SERVER_API_URL + "storage/download/image/$url")
}

fun RequestCreator.onSuccess(callback: () -> Unit): RequestCreator {
    this.fetch(object : Callback {
        override fun onSuccess() = callback()

        override fun onError(e: Exception?) = Unit
    })
    return this
}

fun RequestCreator.onError(callback: (Exception?) -> Unit): RequestCreator {
    this.fetch(object : Callback {
        override fun onSuccess() = Unit

        override fun onError(e: Exception?) = callback(e)
    })
    return this
}

fun RequestCreator.onCompleted(callback: () -> Unit): RequestCreator {
    this.fetch(object : Callback {
        override fun onSuccess() = callback()

        override fun onError(e: Exception?) = callback()
    })
    return this
}