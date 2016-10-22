package com.example.day27_service_remove;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 张样 on 2016/10/14.
 */
public class MyService extends Service {
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            Log.i("android", "handleMessage: ");
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Messenger messenger = new Messenger(mHandler);
        return messenger.getBinder();
    }
}
