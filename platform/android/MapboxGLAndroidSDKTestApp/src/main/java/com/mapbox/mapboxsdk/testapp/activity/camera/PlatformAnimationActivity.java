package com.mapbox.mapboxsdk.testapp.activity.camera;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.NativeCameraPosition;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.testapp.R;

public class PlatformAnimationActivity extends AppCompatActivity implements OnMapReadyCallback {

  private MapboxMap mapboxMap;
  private MapView mapView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_platform_animations);

    mapView = (MapView) findViewById(R.id.mapView);
    if (mapView != null) {
      mapView.onCreate(savedInstanceState);
      mapView.getMapAsync(this);
    }
  }

  @Override
  public void onMapReady(MapboxMap map) {
    mapboxMap = map;

    final NativeCameraPosition cameraPosition = mapView.getCameraPosition();

    // Animate tilt
    ValueAnimator tiltAnimator = ValueAnimator.ofFloat(0f, 60.0f);
    tiltAnimator.setStartDelay(1000);
    tiltAnimator.setDuration(4000);
    tiltAnimator.setInterpolator(new FastOutSlowInInterpolator());
    tiltAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      public void onAnimationUpdate(ValueAnimator animation) {
        cameraPosition.setTilt(((Float) animation.getAnimatedValue()).doubleValue());
      }
    });

    // Animate bearing
    ValueAnimator bearingAnimator = ValueAnimator.ofFloat(0.0f, 160.0f);
    bearingAnimator.setDuration(6000);
    bearingAnimator.setInterpolator(new FastOutLinearInInterpolator());
    bearingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        cameraPosition.setBearing(((Float)animation.getAnimatedValue()).doubleValue());
      }
    });

    // Combine animations and start
    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.setStartDelay(1500);
    animatorSet.play(tiltAnimator);
    animatorSet.play(bearingAnimator);
    animatorSet.start();
  }

  @Override
  protected void onStart() {
    super.onStart();
    mapView.onStart();
  }

  @Override
  protected void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  protected void onStop() {
    super.onStop();
    mapView.onStop();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }
}
