package com.mapbox.mapboxsdk.testapp.mapboxfreezerepro.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.mapbox.mapboxsdk.testapp.R

class EmptyView(context: Context, attributeSet: AttributeSet? = null) : FrameLayout(context, attributeSet) {

    init {
        View.inflate(context, R.layout.empty_view, this)
    }
}
