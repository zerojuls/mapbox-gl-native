package com.mapbox.mapboxsdk.style;

import android.graphics.Bitmap;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.layers.TransitionOptions;
import com.mapbox.mapboxsdk.style.light.Light;
import com.mapbox.mapboxsdk.style.sources.Source;

import java.util.List;

public class Style {

  void loadJson(String json) {

  }

  void loadURL(String url) {

  }

  String getJson() {
    return "";
  }

  String getURL() {
    return "";
  }

  String getName() {
    return "";
  }

  CameraPosition getDefaultCamera() {
    return null;
  }

  TransitionOptions getTransitionOptions() {
    return null;
  }

  void setTransitionOptions(TransitionOptions options) {
  }

  void setLight(Light light) {
  }

  Light getLight() {
    return null;
  }

  Bitmap getImage(String image) {
    return null;
  }

  void addImage(String id, Bitmap bitmap) {

  }

  void removeImage(String id) {

  }

  //@NonNull
  List<Source> getSources() {
    return null;
  }

  @Nullable
  Source getSource(String id) {
    return null;
  }

  void addSource(Source source) {

  }

  void removeSource(Source source) {

  }

  List<Layer> getLayers() {
    return null;
  }

  @Nullable
  Layer getLayer(String layerId) {
    return null;
  }

  public void addLayer(@NonNull Layer layer) {
  }

  public void addLayerBelow(@NonNull Layer layer, @NonNull String below) {
  }

  public void addLayerAbove(@NonNull Layer layer, @NonNull String above) {
  }

  public void addLayerAt(@NonNull Layer layer, @IntRange(from = 0) int index) {
  }

  void removeLayer(String id) {

  }

}
