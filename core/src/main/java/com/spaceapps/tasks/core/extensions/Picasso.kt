package com.spaceapps.tasks.core.extensions

import com.spaceapps.tasks.core.BuildConfig
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

fun Picasso.loadFromBackend(url: String?): RequestCreator {
    return load(BuildConfig.SERVER_API_URL + "storage/download/image/$url")
}