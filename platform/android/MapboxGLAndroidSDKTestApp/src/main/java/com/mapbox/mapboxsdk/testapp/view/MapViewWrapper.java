package com.mapbox.mapboxsdk.testapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import timber.log.Timber;

public class MapViewWrapper extends FrameLayout implements OnMapReadyCallback {

  private MapView mapView;

  public MapViewWrapper(@NonNull Context context) {
    super(context);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    mapView = new MapView(getContext());
    addView(mapView);

    mapView.onCreate(null);
    mapView.getMapAsync(mapboxMap -> {

    });
    mapView.onStart();
    mapView.onResume();
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    removeView(mapView);
  }

  public void beforeRemoveCleanup(){
    mapView.onPause();
    mapView.onStop();
    mapView.onDestroy();
  }

  @Override
  public void onMapReady(MapboxMap mapboxMap) {
    Timber.e("onMapReady");
  }
}
