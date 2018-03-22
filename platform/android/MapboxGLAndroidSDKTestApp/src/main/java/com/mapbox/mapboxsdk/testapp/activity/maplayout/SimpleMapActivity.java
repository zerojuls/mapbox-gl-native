package com.mapbox.mapboxsdk.testapp.activity.maplayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.testapp.R;

import timber.log.Timber;

/**
 * Test activity showcasing a simple MapView without any MapboxMap interaction.
 */
public class SimpleMapActivity extends AppCompatActivity {

  private MapView mapView;
  private double fpsCount;
  private int fpsEventCount;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_simple);

    mapView = (MapView) findViewById(R.id.mapView);
    mapView.onCreate(savedInstanceState);

    mapView.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(final MapboxMap mapboxMap) {
        new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
            runFpsAnimationTest(mapboxMap);
          }
        }, 3000);
      }
    });
  }

  private void runFpsAnimationTest(MapboxMap mapboxMap) {
    mapboxMap.setOnFpsChangedListener(new MapboxMap.OnFpsChangedListener() {

      // ignore first fps value
      private boolean isFirstEvent = true;

      @Override
      public void onFpsChanged(double fps) {
        if (isFirstEvent) {
          isFirstEvent = false;
          return;
        }

        Timber.e("FPS: %s", fps);
        fpsCount += fps;
        fpsEventCount++;
      }
    });

    mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(
      new CameraPosition.Builder()
        .target(new LatLng(-0.719470, 8.752940))
        .zoom(16)
        .build()),
      5000, new MapboxMap.CancelableCallback() {
        @Override
        public void onCancel() {

        }

        @Override
        public void onFinish() {
          double averageFps = fpsCount / fpsEventCount;
          Timber.e("Average FPS: %s", averageFps);
        }
      });
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
  public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }
}
