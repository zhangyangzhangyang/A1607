package com.example.day27_message_b;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 张样 on 2016/10/14.
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Handler mHandler = new Handler(){
        public static final String TAG = "android++";

        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "handleMessage: BBBBBBB");
        }
    };
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //这
        return super.onStartCommand(intent, flags, startId);
    }
}
