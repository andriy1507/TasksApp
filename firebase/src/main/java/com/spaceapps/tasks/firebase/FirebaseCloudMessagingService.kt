package com.spaceapps.tasks.firebase

import android.annotation.SuppressLint
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.spaceapps.tasks.firebase.notification.FirebaseNotificationsBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseCloudMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    @Inject
    lateinit var notificationsBuilder: FirebaseNotificationsBuilder

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        notificationsBuilder.build(message.notification)?.let {
            notificationManager.notify(Random().nextInt(), it)
        }
    }
}