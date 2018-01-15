package com.mapbox.mapboxsdk.testapp.activity.maplayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.offline.OfflineRegion;
import com.mapbox.mapboxsdk.offline.OfflineRegionError;
import com.mapbox.mapboxsdk.offline.OfflineRegionStatus;
import com.mapbox.mapboxsdk.offline.OfflineTilePyramidRegionDefinition;
import com.mapbox.mapboxsdk.testapp.utils.OfflineUtils;
import timber.log.Timber;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Test activity showcasing a simple MapView without any MapboxMap interaction.
 */
public class SimpleMapActivity extends AppCompatActivity implements OfflineManager.CreateOfflineRegionCallback {

  private long timestamp;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    int densityDpi = getResources().getDisplayMetrics().densityDpi;

    LatLng southWest = new LatLng(64.149385, -22.036754);
    LatLng northEast = new LatLng(64.156640, -22.020452);
    LatLngBounds latLngBounds = new LatLngBounds.Builder().include(southWest).include(northEast).build();
    OfflineTilePyramidRegionDefinition definition = new OfflineTilePyramidRegionDefinition(Style.MAPBOX_STREETS, latLngBounds, 17, 18, densityDpi);

    long time = Calendar.getInstance().getTimeInMillis();
    byte[] regionName = OfflineUtils.convertRegionName("test " + getDateCurrentTimeZone(time));
    OfflineManager.getInstance(this).createOfflineRegion(definition, regionName, this);
  }

  @Override
  public void onCreate(OfflineRegion offlineRegion) {
    Timber.e("OfflineRegion created: start your engines!");
    offlineRegion.setObserver(new OfflineRegion.OfflineRegionObserver() {
      @Override
      public void onStatusChanged(OfflineRegionStatus status) {
        // Compute a percentage
        double percentage = status.getRequiredResourceCount() >= 0
          ? (100.0 * status.getCompletedResourceCount() / status.getRequiredResourceCount()) :
          0.0;

        // Debug
        Timber.d("%s/%s resources; %s bytes downloaded.",
          String.valueOf(status.getCompletedResourceCount()),
          String.valueOf(status.getRequiredResourceCount()),
          String.valueOf(status.getCompletedResourceSize()));

        if (status.isComplete()) {
          // Download complete
          Timber.e("Region downloaded");
          offlineRegion.setDownloadState(OfflineRegion.STATE_INACTIVE);
          offlineRegion.setObserver(null);
          long endTime = System.nanoTime();
          long duration = (endTime - timestamp) / 1000000;
          long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
          long seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
          Timber.e("Time to complete was %s:%s", minutes, seconds);
        }
      }

      @Override
      public void onError(OfflineRegionError error) {

      }

      @Override
      public void mapboxTileCountLimitExceeded(long limit) {

      }
    });

    timestamp = System.nanoTime();
    offlineRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);
  }

  @Override
  public void onError(String error) {
    Timber.e("OnError %s", error);
  }

  public String getDateCurrentTimeZone(long timestamp) {
    Calendar calendar = Calendar.getInstance();
    TimeZone tz = TimeZone.getDefault();
    calendar.setTimeInMillis(timestamp * 1000);
    calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    Date currenTimeZone = calendar.getTime();
    return sdf.format(currenTimeZone);
  }
}
