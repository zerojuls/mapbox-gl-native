package com.mapbox.mapboxsdk.testapp.mapboxfreezerepro.fragment

import android.os.Bundle
import android.view.View
import com.mapbox.mapboxsdk.maps.SupportMapFragment
import timber.log.Timber

class CustomMapFragment : SupportMapFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapAsync { map ->
            map.cameraPosition
            with(map.uiSettings) {
                isAttributionEnabled = false
                isCompassEnabled = false
                isLogoEnabled = false
                isDeselectMarkersOnTap = false
                isScrollGesturesEnabled = false
                isZoomGesturesEnabled = false
                isTiltGesturesEnabled = false
                isRotateGesturesEnabled = false
                isDoubleTapGesturesEnabled = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.e("onDestroyView")
    }

    override fun onStop() {
        super.onStop()
        Timber.e("onStop")
    }
}
