package com.spaceapps.tasks

import android.app.Application
import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.di.ApplicationComponent

class TasksApplication : Application(), App {

    private val component by lazy { ApplicationComponent.Initializer().init(this) }

    override fun getProvider() = component

    override fun getContext() = this
}