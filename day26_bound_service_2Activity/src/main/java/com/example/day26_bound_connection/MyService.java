package com.example.day26_bound_connection;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 张样 on 2016/10/13.
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("android++", "onBind: XXXX");
        return new MyBinder();
    }
    class MyBinder extends Binder{

        public String getName(int index){
            return "zhangyang";
        }
    }
}
