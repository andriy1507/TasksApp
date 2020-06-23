package com.spaceapps.tasks.firebase.notification

import android.app.Notification
import com.google.firebase.messaging.RemoteMessage

interface FirebaseNotificationsBuilder {

    fun build(firebaseNotification: RemoteMessage.Notification?): Notification?
}