package com.spaceapps.tasks.di

import android.content.Context
import com.spaceapps.tasks.core.App
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideContext(app: App): Context {
        return app.getContext()
    }
}