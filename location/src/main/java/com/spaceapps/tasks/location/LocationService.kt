package com.spaceapps.tasks.location

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.spaceapps.tasks.core.model.LocationCoordinates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.Channel
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class LocationService : Service() {

    private val request = LocationRequest.create().apply {
        interval = TimeUnit.SECONDS.toMillis(10)
        fastestInterval = TimeUnit.SECONDS.toMillis(5)
        maxWaitTime = TimeUnit.SECONDS.toMillis(30)
        priority = PRIORITY_HIGH_ACCURACY
    }

    private val callback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            result?.lastLocation?.let {
                channel.offer(LocationCoordinates(it.longitude, it.latitude))
            }
        }
    }

    private val locationBinder = LocationBinder()
    val channel = Channel<LocationCoordinates>()

    override fun onBind(intent: Intent?): LocationBinder {
        LocationServices.getFusedLocationProviderClient(applicationContext)
            .requestLocationUpdates(request, callback, Looper.myLooper())
        return locationBinder
    }

    inner class LocationBinder : Binder() {
        val service = this@LocationService
    }
}