package com.androidxx.yangjw.day24_broadcast_1_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * 静态注册的广播基本使用
 * 静态注册：在Androidmanifest.xml文件中的注册，就是静态注册
 * 步骤：
 * 1、创建一个广播接收器
 * 2、在AndroidManifest.xml文件中进行注册
 * 3、配置接收器的频段（配置一个过滤器）
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new MusicRunnable()).start();
    }

    class MusicRunnable implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (Tool.isStop) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.i(TAG, "run: 正在播放音乐");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
