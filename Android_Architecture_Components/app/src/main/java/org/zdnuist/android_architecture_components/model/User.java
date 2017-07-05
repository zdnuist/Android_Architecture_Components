package org.zdnuist.android_architecture_components.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by zd on 2017/7/4.
 */

@Entity
public class User {

  @PrimaryKey
  public int id;

  @ColumnInfo(name = "full_name")
  public String name;

}
