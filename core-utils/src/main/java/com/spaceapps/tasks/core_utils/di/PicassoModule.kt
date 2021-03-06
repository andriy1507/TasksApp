package com.spaceapps.tasks.core_utils.di

import android.content.Context
import com.spaceapps.tasks.remote.di.annotation.RetrofitScope
import com.squareup.picasso.Cache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(ApplicationComponent::class)
class PicassoModule {

    @Provides
    fun providePicasso(
        context: Context,
        @RetrofitScope
        client: OkHttpClient
    ): Picasso {

        return Picasso.Builder(context)
            .downloader(OkHttp3Downloader(client))
            .loggingEnabled(true)
            .build()
    }
}