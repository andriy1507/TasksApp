package com.spaceapps.tasks.account

import android.app.Service
import android.content.Intent
import android.os.IBinder

class SpaceAppsAuthenticatorService : Service() {

    private val authenticator by lazy { SpaceAppsAuthenticator(this) }
    override fun onBind(intent: Intent?): IBinder? = authenticator.iBinder
}