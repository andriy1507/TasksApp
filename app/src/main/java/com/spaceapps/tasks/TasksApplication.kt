package com.spaceapps.tasks

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationManagerCompat
import com.facebook.stetho.Stetho
import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.di.ApplicationComponent
import timber.log.Timber
import javax.inject.Inject

class TasksApplication : Application(), App {

    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    private val component by lazy { ApplicationComponent.Initializer().init(this) }

    override fun getProvider() = component

    override fun getContext() = this

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        initStetho()
        initTimber()
        initFirebaseNotificationsChannel()
    }

    private fun initStetho() = debug { Stetho.initializeWithDefaults(this) }

    private fun initTimber() = debug { Timber.plant(Timber.DebugTree()) }

    private fun initFirebaseNotificationsChannel() = oreo {
        val channel = NotificationChannel(
            getString(R.string.firebase_notifications_channel_id),
            getString(R.string.firebase_notifications_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }

}