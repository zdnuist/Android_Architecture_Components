package org.zdnuist.android_architecture_components;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import org.zdnuist.android_architecture_components.listener.MyListener;
import org.zdnuist.android_architecture_components.livedata.MyLocationLiveData;

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner{

  private final static String TAG = "MainActivity";

  private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

  private TextView mTextView;

  private MyListener myListener;

  private MyLocationLiveData myLocationLiveData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mTextView = (TextView) findViewById(R.id.id_tv);

    myListener = new MyListener(this,getLifecycle());

    myLocationLiveData = new MyLocationLiveData(this);
    myLocationLiveData.observe(this, new Observer<AMapLocation>() {
      @Override
      public void onChanged(@Nullable AMapLocation location) {
        Log.i(TAG, "location:" + location);
        mTextView.setText(location.toStr());
      }
    });
  }

  @Override
  public LifecycleRegistry getLifecycle() {
    return mLifecycleRegistry;
  }
}
