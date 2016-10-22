package com.androidxx.yangjw.day20_handler_right_demo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Handler正确的写法
 * 一个避免出现OOM异常的写法
 * Java垃圾回收的条件：
 * 某一个对象没有任何其他对象来引用它，则gc垃圾回收器会将它回收
 *
 * 当Handler延迟发送消息，即使Activity退出了，但是Handler还是持有一个Activity的引用，
 * 所以此时的Activity不是真的销毁了，内存中还存在。
 * Handler的延迟发送消息，会阻碍Activity的正常销毁和被回收。
 *
 *
 * Handler的机制原理：
 * 1、Handler
 * 2、Message
 * 3、MessageQueue
 * 4、Looper
 *
 */
public class MainActivity extends AppCompatActivity {

    private TextView mShowText;

    private String TAG = "androidxx";

    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowText = (TextView) findViewById(R.id.main_show_txt);
        myHandler = new MyHandler(this);
        new Thread(new MyRunnable()).start();
        Looper.loop();
//        finish();

//        User.Men men = new User().new Men();
//        User.women women = new User.women();
    }


    class MyRunnable implements Runnable {

        @Override
        public void run() {
            Message message = Message.obtain();
            message.obj = "zhangsan";
            myHandler.sendMessageDelayed(message,5000);
        }
    }


    /**
     * 解决Handler出现OOM异常的问题：
     * 1、使用静态的内部类不会对外部类的对象有影响，静态内部类是一个独立的类。
     * 2、使用弱引用：就是当需要回收此对象的时候，即使有对象在引用他，也会被回收
     */
    static class MyHandler extends Handler {
        /**
         * 弱引用
         */
        private WeakReference<MainActivity> weakReference;

        public MyHandler(MainActivity mainActivity) {
            weakReference = new WeakReference<MainActivity>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            try {
                MainActivity mainActivity = weakReference.get();
                mainActivity.mShowText.setText(msg.obj.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
