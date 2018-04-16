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
    mapView = new MapView(getContext());
    addView(mapView);

    mapView.onCreate(null);
    mapView.getMapAsync(this);
    mapView.onStart();
    mapView.onResume();
  }

  @Override
  public void removeAllViews() {
    mapView.onPause();
    mapView.onStop();
    mapView.onDestroy();
    super.removeAllViews();
  }

  @Override
  public void onMapReady(MapboxMap mapboxMap) {
    Timber.e("onMapReady");
  }
}
