package org.zdnuist.android_architecture_components.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.zdnuist.android_architecture_components.model.User;
import org.zdnuist.android_architecture_components.utils.ThreadUtil;

/**
 * Created by zd on 2017/7/4.
 */

public class MyViewModel extends ViewModel {

  private Handler mHandler = new Handler(Looper.getMainLooper());

  private final static String TAG = "MyViewModel";

  private MutableLiveData<List<User>> users;

  public LiveData<List<User>> getUsers(){
    if(users == null){
      users = new MutableLiveData<>();
      loadUsers();
    }

    return users;
  }

  private void loadUsers(){
    ThreadUtil.THREAD_POOL.submit(new Runnable() {
      @Override
      public void run() {
        Log.i(TAG,"beging load data");
        final List<User> list = new ArrayList<User>();
        for (int i = 0 ; i < 10000; i++){
          User user = new User();
          user.id = i;
          user.name = "name_"+i;
          list.add(user);
        }
        mHandler.post(new Runnable() {
          @Override
          public void run() {
            users.setValue(list);
            Log.i(TAG,"end load data");
          }
        });

      }
    });
  }

}
