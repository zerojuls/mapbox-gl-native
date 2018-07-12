package com.mapbox.mapboxsdk.maps.renderer;

import android.content.Context;
import android.support.annotation.CallSuper;

import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.storage.FileSource;
import com.mapbox.mapboxsdk.utils.ColorUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * The {@link MapRenderer} encapsulates the GL thread.
 * <p>
 * Performs actions on the GL thread to manage the GL resources and
 * render on the one end and acts as a scheduler to request work to
 * be performed on the GL thread on the other.
 */
public abstract class MapRenderer implements MapRendererScheduler {

  // Holds the pointer to the native peer after initialisation
  private long nativePtr = 0;

  // The color shown when the map style is loading
  private final float[] foregroundRgbaColor;

  private MapboxMap.OnFpsChangedListener onFpsChangedListener;

  public MapRenderer(Context context, String localIdeographFontFamily, int foregroundLoadColor) {
    FileSource fileSource = FileSource.getInstance(context);
    float pixelRatio = context.getResources().getDisplayMetrics().density;
    String programCacheDir = context.getCacheDir().getAbsolutePath();

    // convert android int color to GL rgba, range 0-1
    foregroundRgbaColor = ColorUtils.colorToGlRgbaArray(foregroundLoadColor);

    // Initialise native peer
    nativeInitialize(this, fileSource, pixelRatio, programCacheDir, localIdeographFontFamily);
  }

  public void onStart() {
    // Implement if needed
  }

  public void onPause() {
    // Implement if needed
  }

  public void onResume() {
    // Implement if needed
  }

  public void onStop() {
    // Implement if needed
  }

  public void onDestroy() {
    // Implement if needed
  }

  public void setOnFpsChangedListener(MapboxMap.OnFpsChangedListener listener) {
    onFpsChangedListener = listener;
  }

  @CallSuper
  protected void onSurfaceCreated(GL10 gl, EGLConfig config) {
    nativeOnSurfaceCreated();
  }

  @CallSuper
  protected void onSurfaceChanged(GL10 gl, int width, int height) {
    gl.glViewport(0, 0, width, height);
    nativeOnSurfaceChanged(width, height);
  }

  @CallSuper
  protected void onDrawFrame(GL10 gl) {
    // clear color with the foreground load color #10990
    // on low end devices there is a race condition between
    // when the gl surface is loaded and the onMapReady is called
    gl.glClearColor(foregroundRgbaColor[0], foregroundRgbaColor[1], foregroundRgbaColor[2], foregroundRgbaColor[3]);

    nativeRender();

    if (onFpsChangedListener != null) {
      updateFps();
    }
  }

  /**
   * May be called from any thread.
   * <p>
   * Called from the native peer to schedule work on the GL
   * thread. Explicit override for easier to read jni code.
   *
   * @param runnable the runnable to execute
   * @see MapRendererRunnable
   */
  @CallSuper
  void queueEvent(MapRendererRunnable runnable) {
    this.queueEvent((Runnable) runnable);
  }

  private native void nativeInitialize(MapRenderer self,
                                       FileSource fileSource,
                                       float pixelRatio,
                                       String programCacheDir,
                                       String localIdeographFontFamily);

  @CallSuper
  @Override
  protected native void finalize() throws Throwable;

  private native void nativeOnSurfaceCreated();

  private native void nativeOnSurfaceChanged(int width, int height);

  private native void nativeRender();

  private long frames;
  private long timeElapsed;

  private void updateFps() {
    frames++;
    long currentTime = System.nanoTime();
    double fps = 0;
    if (currentTime - timeElapsed >= 1) {
      fps = frames / ((currentTime - timeElapsed) / 1E9);
      onFpsChangedListener.onFpsChanged(fps);
      timeElapsed = currentTime;
      frames = 0;
    }
  }
}
