package com.example.day_26_bound;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 张样 on 2016/10/13.
 * bindService的创建步骤
 * 1，重写onBind方法
 * 2.创建一个类extendsBinder
 * 3在onBind方法中返回第二步创建的类对象
 * 4可以在activity中接受到IBinder对象
 */
public class Myservice extends Service {
    /**
     * 当service是bindService启动时会执行此方法
     * @param intent 意图对象
     * @return  IBinder 对象如一个纽带。链接activity和service
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("android+++++", "onBind: ");
        return new MyBinder();
    }
    class MyBinder extends Binder{
        public String getName(int index){
            return "zhangsan";
        }
    }

}
