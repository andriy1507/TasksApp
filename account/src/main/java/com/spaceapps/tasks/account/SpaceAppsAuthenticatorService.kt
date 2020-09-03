package com.spaceapps.tasks.account

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SpaceAppsAuthenticatorService : Service() {

    @Inject
    lateinit var authenticator:SpaceAppsAuthenticator

    override fun onBind(intent: Intent?): IBinder? = authenticator.iBinder
}