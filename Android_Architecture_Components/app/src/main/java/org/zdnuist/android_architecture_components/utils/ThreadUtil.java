package org.zdnuist.android_architecture_components.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zd on 2017/7/4.
 */

public class ThreadUtil {

  public static ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

}
