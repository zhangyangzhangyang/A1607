package com.example.day27_helloworld;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 张样 on 2016/10/14.
 */
public class MyService extends Service {

    private Handler mHandler;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("A1");
        handlerThread.start();//必须先start，才能获得子线程中的looper对象
        mHandler = new Handler(handlerThread.getLooper()){
            public static final String TAG = "android";

            @Override
            public void handleMessage(Message msg) {
                int index = msg.what;
                while(index ++ < 15){
                    Log.i(TAG, "handleMessage: "+ index);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler.sendEmptyMessage(0);
        return super.onStartCommand(intent, flags, startId);
    }
}
