package com.spaceapps.tasks.firebase.notification.impl

import android.app.Notification
import android.content.Context
import android.graphics.Color
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage
import com.spaceapps.tasks.firebase.R
import com.spaceapps.tasks.firebase.notification.FirebaseNotificationsBuilder
import javax.inject.Inject

class FirebaseNotificationsBuilderImpl @Inject constructor(
    private val context: Context
) : FirebaseNotificationsBuilder {

    override fun build(firebaseNotification: RemoteMessage.Notification?): Notification? {
        return firebaseNotification?.let {
            NotificationCompat.Builder(context, getChannelId())
                .setSmallIcon(R.drawable.ic_firebase_notification)
                .setColorized(true)
                .setColor(Color.RED)
                .setContentTitle(firebaseNotification.title)
                .setContentText(firebaseNotification.body)
                .build()
        }
    }

    private fun getChannelId() = context.getString(R.string.firebase_notifications_channel_id)

}