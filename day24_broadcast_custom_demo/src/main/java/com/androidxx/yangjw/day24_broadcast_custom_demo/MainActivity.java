package com.androidxx.yangjw.day24_broadcast_custom_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 完成自定义的广播
 * 1、创建一个广播接收器
 * 2、创建一个广播发送器
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        //发送广播
        /**
         * 发送的action必须等接收器的action
         */
        Intent intent = new Intent("androidxx.demo.custom.SEND");
        intent.putExtra("name","zhangsan");
        sendBroadcast(intent);
    }
}
