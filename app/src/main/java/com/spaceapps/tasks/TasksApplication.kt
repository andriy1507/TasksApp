package com.spaceapps.tasks

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationManagerCompat
import com.facebook.stetho.Stetho
import com.spaceapps.tasks.core.extensions.release
import com.spaceapps.tasks.logging.DebugTree
import com.spaceapps.tasks.logging.ReleaseTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class TasksApplication : Application() {

    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate() {
        super.onCreate()
        initStetho()
        initTimber()
        initNotificationsChannels()
    }

    private fun initStetho() = debug { Stetho.initializeWithDefaults(this) }

    private fun initTimber() {
        debug { Timber.plant(DebugTree()) }
        release { Timber.plant(ReleaseTree()) }
    }

    private fun initNotificationsChannels() = oreo {
        val firebaseChannel = NotificationChannel(
            getString(R.string.firebase_notifications_channel_id),
            getString(R.string.firebase_notifications_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )
        val mediaChannel = NotificationChannel(
            getString(R.string.media_player_notifications_channel_id),
            getString(R.string.media_player_notifications_channel_id),
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.apply {
            createNotificationChannel(firebaseChannel)
            createNotificationChannel(mediaChannel)
        }
    }

}