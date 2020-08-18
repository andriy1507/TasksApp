package com.spaceapps.tasks.account

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.spaceapps.tasks.account.di.AuthenticatorServiceComponent
import javax.inject.Inject

class SpaceAppsAuthenticatorService : Service() {

    @Inject
    lateinit var authenticator:SpaceAppsAuthenticator

    override fun onCreate() {
        super.onCreate()
        AuthenticatorServiceComponent.init(this).inject(this)
    }

    override fun onBind(intent: Intent?): IBinder? = authenticator.iBinder
}