package org.zdnuist.android_architecture_components.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;
import org.zdnuist.android_architecture_components.model.User;

/**
 * Created by zd on 2017/7/4.
 */

@Dao
public interface UserDao {

  @Query("SELECT * FROM user")
  List<User> getAll();

  @Insert
  void insertAll(User... users);
}
