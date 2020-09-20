package com.spaceapps.tasks.maps

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.spaceapps.tasks.core.extensions.viewBinding
import com.spaceapps.tasks.location.LocationService
import com.spaceapps.tasks.maps.databinding.FragmentGoogleMapsBinding
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class GoogleMapsFragment : Fragment(R.layout.fragment_google_maps), OnMapReadyCallback,
    ServiceConnection {

    private val binding by viewBinding(FragmentGoogleMapsBinding::bind)

    private var currentLocation: LatLng? = null

    private var googleMap: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let { context ->
            context.bindService(
                Intent(context, LocationService::class.java),
                this, Context.BIND_AUTO_CREATE
            )
        }
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            applySystemWindowInsetsToPadding(top = true)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
        val map = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        map?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map
        map?.uiSettings?.isMyLocationButtonEnabled = false
        binding.buttonCurrentLocation.setOnClickListener {
            currentLocation?.let { location ->
                map?.animateCamera(CameraUpdateFactory.newLatLng(location))
            }
        }
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.normal_map -> map?.mapType = GoogleMap.MAP_TYPE_NORMAL
                R.id.hybrid_map -> map?.mapType = GoogleMap.MAP_TYPE_HYBRID
                R.id.satellite_map -> map?.mapType = GoogleMap.MAP_TYPE_SATELLITE
                R.id.terrain_map -> map?.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
            true
        }
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            map?.isMyLocationEnabled = true
        } else {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ), LOCATION_PERMISSION_REQUEST
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST -> {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                googleMap?.isMyLocationEnabled = true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.let { context ->
            context.stopService(
                Intent(
                    context,
                    LocationService::class.java
                )
            )
        }
    }

    override fun onServiceConnected(p0: ComponentName?, binder: IBinder?) {
        val locationChannel = (binder as LocationService.LocationBinder).service.channel
        CoroutineScope(Main).launch {
            for (location in locationChannel) {
                currentLocation = LatLng(location.latitude, location.longitude)
            }
        }
    }

    override fun onServiceDisconnected(p0: ComponentName?) = Unit

    companion object {
        private const val LOCATION_PERMISSION_REQUEST = 0x1234
    }
}