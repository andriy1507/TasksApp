package com.spaceapps.tasks.remote.di

import com.spaceapps.tasks.remote.di.annotation.RetrofitScope
import okhttp3.OkHttpClient

interface OkHttpClientProvider {

    @RetrofitScope
    fun provideOkHttpClient(): OkHttpClient
}