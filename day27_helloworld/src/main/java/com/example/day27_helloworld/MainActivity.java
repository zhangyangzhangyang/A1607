package com.example.day27_helloworld;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * TandlerThread的基本使用
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View view){
        //开启子线程
        HandlerThread thread = new HandlerThread("后刘");
        //启动线程
        thread.start();
        //Looper负责Handler的消息的发送方式
        //thread.getLooper()表示获取一个子线程的looper对象
        Handler handler = new Handler(thread.getLooper()){
            public static final String TAG = "android++";

            //运行在子线程中
            @Override
            public void handleMessage(Message msg) {
                //可以执行耗时操作
                Log.i(TAG, "handleMessage: "+msg);
            }
        };
        handler.sendEmptyMessage(1);
    }
    public void start(View view){
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }
}
