package org.zdnuist.android_architecture_components;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import org.zdnuist.android_architecture_components.database.AppDatabase;
import org.zdnuist.android_architecture_components.model.User;
import org.zdnuist.android_architecture_components.utils.ThreadUtil;

/**
 * Created by zd on 2017/7/4.
 */

public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener{
  private final static String TAG = "DatabaseActivity";

  private TextView showView;
  private Button qryBtn,insertBtn,deleteBtn;

  private AppDatabase db;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_database);

    showView = (TextView) findViewById(R.id.textView);
    qryBtn = (Button) findViewById(R.id.button);
    insertBtn = (Button) findViewById(R.id.button2);
    deleteBtn = (Button) findViewById(R.id.button3);
    qryBtn.setOnClickListener(this);
    insertBtn.setOnClickListener(this);
    deleteBtn.setOnClickListener(this);

    initDatabase();
  }

  private void initDatabase(){
    db = Room.databaseBuilder(getApplicationContext(),
        AppDatabase.class, "aac_db").build();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.button:
        ThreadUtil.THREAD_POOL.submit(new Runnable() {
          @Override
          public void run() {
            List<User>  list = db.userDao().getAll();
            Log.i(TAG,"list:" + list.size());
          }
        });

        break;
      case R.id.button2:
        ThreadUtil.THREAD_POOL.submit(new Runnable() {
          @Override
          public void run() {
            User user = new User();
            user.id = 0;
            user.name = "bill";
            db.userDao().insertAll(user);
          }
        });

        break;
      case R.id.button3:

        break;
    }
  }
}
