package com.example.day26_helloword;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 张样 on 2016/10/13.
 */
public class Myservice extends Service {
    /**
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * service启动时执行的方法
     * @param intent activity启动service石传入的intent对象
     * @param flags service启动的方式..取值跟返回值相关联
     * @param startId service的一个标识id，每次启动service时此id会递增
     * @return 能决定是黏性服务会非
     * START_STICKY :是黏性服务。。当被异常结束掉，系统会自动将其重启
     * START_STICKY_COMPATIBILITY 向下兼容的黏性服务.为了在anfdroid2.0以下的版本不出错
     * START_REDELIVER_INTENT 黏性服务，当服务呗系统异常结束掉时，系统会自动拉起服务，并且会传入一个intent
     *
     * START_NOT_STICKY非黏性服务
     *  stopSelf();结束所有service
     *  stopSelf(startId);结束当前service
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("android+++", "onStartCommand: ");
        stopSelf();
        stopSelf(startId);

        return START_NOT_STICKY;
    }

    /**
     * service呗销毁是执行此方法
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("android_____________", "onDestroy: ");
    }
}
