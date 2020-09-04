package com.spaceapps.tasks.maps

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.spaceapps.tasks.core_ui.BaseFragment
import com.spaceapps.tasks.maps.databinding.FragmentGoogleMapsBinding

class GoogleMapsFragment : BaseFragment(), OnMapReadyCallback {

    override val binding by lazy { FragmentGoogleMapsBinding.inflate(layoutInflater) }

    override fun onMapReady(map: GoogleMap?) {

    }
}