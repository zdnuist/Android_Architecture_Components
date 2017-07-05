package org.zdnuist.android_architecture_components.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import org.zdnuist.android_architecture_components.dao.UserDao;
import org.zdnuist.android_architecture_components.model.User;

/**
 * Created by zd on 2017/7/4.
 */
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

  public abstract UserDao userDao();
}
