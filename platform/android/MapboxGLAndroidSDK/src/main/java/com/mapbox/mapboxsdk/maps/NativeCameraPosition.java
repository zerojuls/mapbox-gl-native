package com.mapbox.mapboxsdk.maps;

public class NativeCameraPosition {

  private NativeMapView nativeMapView;

  public NativeCameraPosition(NativeMapView nativeMapView) {
    this.nativeMapView = nativeMapView;
  }

  public void setTilt(double tilt) {
    nativeMapView.setPitch(tilt, 0);
  }

  public void setBearing(double bearing) {
    nativeMapView.setBearing(bearing);
  }
}
