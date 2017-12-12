package com.mapbox.mapboxsdk.testapp.activity.maplayout;

import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.SurfaceView;

import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.testapp.R;

/**
 * Test activity showcasing a simple MapView without any MapboxMap interaction.
 */
public class SimpleMapActivity extends AppCompatActivity {

  private MapView mapView;

  private GLSurfaceView surfaceView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_simple);

    mapView = (MapView) findViewById(R.id.mapView);
     surfaceView = (GLSurfaceView) mapView.findViewById(R.id.surfaceView);
    surfaceView.setZOrderOnTop(true); // this moves the surface above the view hierarchy
    surfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT); // this allows for translucent surface background
    mapView.onCreate(savedInstanceState);
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
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        surfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 8);
      }
    },5000);
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
