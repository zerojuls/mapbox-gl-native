package com.mapbox.mapboxsdk.testapp.mapboxfreezerepro.views

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.widget.FrameLayout
import android.view.View
import com.mapbox.mapboxsdk.testapp.R
import com.mapbox.mapboxsdk.testapp.mapboxfreezerepro.fragment.CustomMapFragment
import timber.log.Timber

const val TAG = "tag"
class MapView(context: Context,
              attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    private val fragmentManager: FragmentManager

    init {
        fragmentManager = (context as AppCompatActivity).supportFragmentManager
        View.inflate(context, R.layout.map_view, this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Timber.e("onAttachedToWindow")
        val fragment = CustomMapFragment()
        fragmentManager.beginTransaction()
            .add(
                R.id.mapContainer, fragment,
                TAG
            )
            .commit()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Timber.e("onDetachedToWindow")
//        val fragment = fragmentManager.findFragmentByTag(TAG)
//        fragmentManager.beginTransaction()
//            .remove(fragment)
//            .commitNowAllowingStateLoss()
    }
}
