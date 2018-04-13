package com.mapbox.mapboxsdk.testapp.mapboxfreezerepro

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mapbox.mapboxsdk.testapp.R
import com.mapbox.mapboxsdk.testapp.mapboxfreezerepro.views.EmptyView
import com.mapbox.mapboxsdk.testapp.mapboxfreezerepro.views.MapView
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var empty = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentContainer.addView(EmptyView(this))
        button.setOnClickListener {
            Timber.e("removeAllViews")

            if (!empty) {
                val fragment = supportFragmentManager.findFragmentByTag(com.mapbox.mapboxsdk.testapp.mapboxfreezerepro.views.TAG)
                if (fragment != null) {
                    supportFragmentManager.beginTransaction()
                            .remove(fragment)
                            .commitNowAllowingStateLoss()
                }
            }

            fragmentContainer.removeAllViews()
            if (empty) {
                fragmentContainer.addView(
                        MapView(
                                this
                        )
                )
                empty = false
            } else {
                fragmentContainer.addView(
                        EmptyView(
                                this
                        )
                )
                empty = true
            }
        }
    }
}
