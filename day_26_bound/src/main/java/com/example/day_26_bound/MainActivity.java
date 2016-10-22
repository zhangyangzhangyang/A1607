package com.example.day_26_bound;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Bound的service
 * 1创建一个类继承service
 * 2在xml中注册service
 * 3绑定服务
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View view ){
        Intent intent  = new Intent(this, Myservice.class);
        /**
         * 1.intent对象
         * 2.serviceConnerction的对象
         * 3用来决定service的创建方式，标志位
         * BIND_AUTO_CREATE.表示绑定服务的时候，若服务不存在就自动创建
         */

        bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        /**
         * 当activity和service建立连接成功执行
         * @param name intent里面的属性
         * @param service service中的onBind方法的返回值
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("android+++", "onServiceConnected: ++++++++++++");
            Myservice.MyBinder binder = (Myservice.MyBinder) service;
            String name1 = binder.getName(0);
            Log.i("android", "onServiceConnected: "+name1);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑定
        unbindService(serviceConnection);
    }
}
