package com.spaceapps.tasks.maps

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.maps.databinding.FragmentGoogleMapsBinding
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding

class GoogleMapsFragment : BaseFragment(), OnMapReadyCallback {

    override val binding by lazy { FragmentGoogleMapsBinding.inflate(layoutInflater) }

    private val toolbar by lazy { binding.toolbar }
    private var googleMap: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            applySystemWindowInsetsToPadding(top = true)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
        val map = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        map?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.normal_map -> map?.mapType = GoogleMap.MAP_TYPE_NORMAL
                R.id.hybrid_map -> map?.mapType = GoogleMap.MAP_TYPE_HYBRID
                R.id.satellite_map -> map?.mapType = GoogleMap.MAP_TYPE_SATELLITE
                R.id.terrain_map -> map?.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
            true
        }
        map?.addMarker(MarkerOptions().position(LatLng(23.0, 23.0)))
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

    companion object {
        private const val LOCATION_PERMISSION_REQUEST = 0x1234
    }
}