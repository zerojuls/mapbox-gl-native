package com.mapbox.mapboxsdk.testapp.activity.maplayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.mapbox.mapboxsdk.testapp.R;
import com.mapbox.mapboxsdk.testapp.view.EmptyView;
import com.mapbox.mapboxsdk.testapp.view.MapViewWrapper;

public class TabViewActivity extends AppCompatActivity {

  private boolean empty = true;
  private MapViewWrapper mapViewWrapper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_tab);

    ViewGroup viewContainer = (ViewGroup) findViewById(R.id.container);
    findViewById(R.id.button).setOnClickListener(v -> {
      if (mapViewWrapper != null) {
        mapViewWrapper.beforeRemoveCleanup();
        mapViewWrapper = null;
      }
      viewContainer.removeAllViews();

      if (empty) {
        viewContainer.addView(mapViewWrapper = new MapViewWrapper(TabViewActivity.this));
      } else {
        viewContainer.addView(new EmptyView(this));
      }
      empty = !empty;
    });
  }
}
