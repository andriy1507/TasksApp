package com.spaceapps.tasks.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
interface ApplicationWearableModule {

    @Binds
    fun bindContext(@ApplicationContext context: Context): Context
}