package org.zdnuist.android_architecture_components.listener;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;

/**
 * Created by zd on 2017/7/4.
 */

public class MyListener implements LifecycleObserver {

  private final static String TAG = "MyListener";

  private Lifecycle mLifecycle;

  public MyListener(Context context,Lifecycle lifecycle){

    mLifecycle = lifecycle;
    mLifecycle.addObserver(this);
  }

  @OnLifecycleEvent(Event.ON_START)
  void start(){
    Log.i(TAG , "start");
  }

  @OnLifecycleEvent(Event.ON_STOP)
  void stop(){
    Log.i(TAG , "stop");
  }

  @OnLifecycleEvent(Event.ON_DESTROY)
  void destroy(){
    Log.i(TAG, "destroy");
    mLifecycle.removeObserver(this);
  }

}
