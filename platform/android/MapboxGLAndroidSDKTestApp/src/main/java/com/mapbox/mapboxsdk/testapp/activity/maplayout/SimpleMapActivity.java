package com.mapbox.mapboxsdk.testapp.activity.maplayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.testapp.R;

import timber.log.Timber;

/**
 * Test activity showcasing a simple MapView without any MapboxMap interaction.
 */
public class SimpleMapActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_simple);
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.add(R.id.fragment_container, new MapFragment());
    transaction.commit();
  }

  public static class MapFragment extends Fragment{

    private MapView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      super.onCreateView(inflater, container, savedInstanceState);
      return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      FrameLayout mapFrameLayout = (FrameLayout) view.findViewById(R.id.mapview_container);
      MapboxMapOptions mapboxMapOptions = new MapboxMapOptions();
      mapFrameLayout.addView(map = new MapView(view.getContext(), mapboxMapOptions));
      map.onCreate(savedInstanceState);
      map.getMapAsync(new OnMapReadyCallback() {
        @Override
        public void onMapReady(MapboxMap mapboxMap) {
          Timber.v("OnMapReady invoked");
        }
      });
    }

    @Override
    public void onStart() {
      super.onStart();
      map.onStart();
    }

    @Override
    public void onResume() {
      super.onResume();
      map.onResume();
    }

    @Override
    public void onPause() {
      super.onPause();
      map.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
      super.onSaveInstanceState(outState);
      map.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
      super.onStop();
      map.onStop();
    }

    @Override
    public void onLowMemory() {
      super.onLowMemory();
      map.onLowMemory();
    }

    @Override
    public void onDestroyView() {
      super.onDestroyView();
      map.onDestroy();
    }
  }
}
