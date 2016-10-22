package com.example.day26_bound_connection;

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
 * l两个activity绑定同一个activity时生命周期的执行
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View view){
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
    }
    public void onClick(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        public static final String TAG = "android++";

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            MyService.MyBinder binder = (MyService.MyBinder) service;
            Log.i(TAG, "onServiceConnected: ++"+"第一个界面的连接成功");

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
