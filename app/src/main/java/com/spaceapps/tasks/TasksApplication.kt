package com.spaceapps.tasks

import android.app.Application
import com.facebook.stetho.Stetho
import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.di.ApplicationComponent
import timber.log.Timber

class TasksApplication : Application(), App {

    private val component by lazy { ApplicationComponent.Initializer().init(this) }

    override fun getProvider() = component

    override fun getContext() = this

    override fun onCreate() {
        super.onCreate()
        initStetho()
        initTimber()
    }

    private fun initStetho() = debug { Stetho.initializeWithDefaults(this) }

    private fun initTimber() = debug { Timber.plant(Timber.DebugTree()) }

}