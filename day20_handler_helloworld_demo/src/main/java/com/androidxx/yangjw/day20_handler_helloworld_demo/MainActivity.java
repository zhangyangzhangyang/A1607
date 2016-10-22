package com.androidxx.yangjw.day20_handler_helloworld_demo;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Handler的基本使用
 * Handler是用来在2个线程之间传递数据的一个工具
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private TextView mTextView;

    private Handler mHandler = new Handler() {
        /**
         * 处理子线程发送过来的数据的（接收包裹）
         * 此处的handleMessage运行在主线中
         * @param msg sendMessage发送过来的数据
         */
        @Override
        public void handleMessage(Message msg) {
//            String data = msg.obj.toString();
            int what = msg.what;
            mTextView.setText("--" + what);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.main_show_txt);
        //开启线程执行耗时的操作
        new Thread(new NetRunnable()).start();
    }


    class NetRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String data = "zhangsan";
            //Message相当于快递包裹
//            Message message = new Message();
            //实际项目开发中创建Message的方式
            /**
             * 实际项目开发中创建Message的方式
             * obtain()会获取系统已经存在的message对象，
             * 如果没有已经存在的message对象，则会重新创建
             */
            Message message = Message.obtain();
            //包裹中的物品
            message.obj = data;
            /**
             * sendMessage内部其实调用的是sendMessageDelayed
             */
//            mHandler.sendMessage(message);
            Log.i(TAG, "run: ");
            //延迟发送消息
            /**
             * 参数1：Message对象
             * 参数2：延迟的时间，单位是毫秒
             */
//            mHandler.sendMessageDelayed(message,3000);

            long time = SystemClock.uptimeMillis() + 3000;
            /**
             * sendMessageAtTime类似sendMessageDelayed，都是表示延迟发送消息。
             * sendMessageDelayed最终执行的是sendMessageAtTime方法
             */
//            mHandler.sendMessageAtTime(message,time);

            /**
             * 发送一个空消息（并不是表示没有Message对象，而是没有设置Message对象中的obj等属性）
             */
//            mHandler.sendEmptyMessage(2);
//            mHandler.sendEmptyMessageDelayed(2,2000);
            mHandler.sendEmptyMessageAtTime(2,time);
        }
    }
}
