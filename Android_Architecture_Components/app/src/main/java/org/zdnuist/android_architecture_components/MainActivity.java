package org.zdnuist.android_architecture_components;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import java.util.List;
import org.zdnuist.android_architecture_components.listener.MyListener;
import org.zdnuist.android_architecture_components.livedata.MyLocationLiveData;
import org.zdnuist.android_architecture_components.model.User;
import org.zdnuist.android_architecture_components.viewmodel.MyViewModel;

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner{

  private final static String TAG = "MainActivity";

  private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

  private TextView mTextView,mTextView2;

  private MyListener myListener;

  private MyLocationLiveData myLocationLiveData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mTextView = (TextView) findViewById(R.id.id_tv);
    mTextView2 = (TextView) findViewById(R.id.id_tv_2);
    mTextView2.setText("Main");

    myListener = new MyListener(this,getLifecycle());

    myLocationLiveData = MyLocationLiveData.get(this);
    myLocationLiveData.observe(this, new Observer<AMapLocation>() {
      @Override
      public void onChanged(@Nullable AMapLocation location) {
        Log.i(TAG, "location:" + location);
        mTextView.setText(location.toStr());
      }
    });

    MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
    myViewModel.getUsers().observe(this, new Observer<List<User>>() {
      @Override
      public void onChanged(@Nullable List<User> users) {
        Log.i(TAG,"size:" + users.size());
        mTextView2.setText(users.size()+"");
      }
    });

  }

  @Override
  public LifecycleRegistry getLifecycle() {
    return mLifecycleRegistry;
  }
}
