package com.mapbox.mapboxsdk.style;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.maps.Image;
import com.mapbox.mapboxsdk.style.layers.CannotAddLayerException;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.layers.TransitionOptions;
import com.mapbox.mapboxsdk.style.light.Light;
import com.mapbox.mapboxsdk.style.sources.CannotAddSourceException;
import com.mapbox.mapboxsdk.style.sources.Source;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Style {

  private Style(){

  }

  public static Style fromJson(String json){
    return new Style();
  }

  public static Style fromUrl(String url) {
    return new Style();
  }

  public void addImage(String id, Bitmap image){
    if (image.getConfig() != Bitmap.Config.ARGB_8888) {
      image = image.copy(Bitmap.Config.ARGB_8888, false);
    }
    float pixelRatio = image.getDensity() / DisplayMetrics.DENSITY_DEFAULT;
    ByteBuffer buffer = ByteBuffer.allocate(image.getByteCount());
    image.copyPixelsToBuffer(buffer);
    nativeAddImage(id, image.getWidth(), image.getHeight(), pixelRatio, buffer.array());
  }

  public void addImages(@NonNull HashMap<String, Bitmap> bitmapHashMap) {
    //noinspection unchecked
    new BitmapImageConversionTask(this).execute(bitmapHashMap);
  }

  public native String getJson();

  public native String getURL();

  public native String getName();

  public native CameraPosition getDefaultCamera();

  public native TransitionOptions getTransitionOptions();

  public native void setTransitionOptions(TransitionOptions options);

  public native void setLight(Light light);

  public native void getLight();

  public native Bitmap getImage(String image);

  private native void nativeAddImage(String name, int width, int height, float pixelRatio, byte[] array);

  private native void nativeAddImages(Image[] images);

  public native void removeImage(String id);

  private native Layer nativeGetLayer(String layerId);

  private native void nativeAddLayer(long layerPtr, String before) throws CannotAddLayerException;

  private native void nativeAddLayerAbove(long layerPtr, String above) throws CannotAddLayerException;

  private native void nativeAddLayerAt(long layerPtr, int index) throws CannotAddLayerException;

  private native Layer nativeRemoveLayerById(String layerId);

  private native void nativeRemoveLayer(long layerId);

  private native Layer nativeRemoveLayerAt(int index);

  private native Source[] nativeGetSources();

  private native Source nativeGetSource(String sourceId);

  private native void nativeAddSource(Source source, long sourcePtr) throws CannotAddSourceException;

  private native void nativeRemoveSource(Source source, long sourcePtr);

  private static class BitmapImageConversionTask extends AsyncTask<HashMap<String, Bitmap>, Void, List<Image>> {

    private Style style;

    BitmapImageConversionTask(Style style) {
      this.style = style;
    }

    @Override
    protected List<Image> doInBackground(HashMap<String, Bitmap>... params) {
      HashMap<String, Bitmap> bitmapHashMap = params[0];

      List<Image> images = new ArrayList<>();
      ByteBuffer buffer;
      String name;
      Bitmap bitmap;

      for (Map.Entry<String, Bitmap> stringBitmapEntry : bitmapHashMap.entrySet()) {
        name = stringBitmapEntry.getKey();
        bitmap = stringBitmapEntry.getValue();

        if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
          bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        }

        buffer = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(buffer);

        float density = bitmap.getDensity() == Bitmap.DENSITY_NONE ? Bitmap.DENSITY_NONE : bitmap.getDensity();
        float pixelRatio = density / DisplayMetrics.DENSITY_DEFAULT;

        images.add(new Image(buffer.array(), pixelRatio, name, bitmap.getWidth(), bitmap.getHeight()));
      }

      return images;
    }

    @Override
    protected void onPostExecute(List<Image> images) {
      super.onPostExecute(images);
      if (style != null) {
        style.nativeAddImages(images.toArray(new Image[images.size()]));
      }
    }
  }

}
