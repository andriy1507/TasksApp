package com.spaceapps.tasks.remote.di

import okhttp3.OkHttpClient

interface OkHttpClientProvider {

    fun provideOkHttpClient(): OkHttpClient
}