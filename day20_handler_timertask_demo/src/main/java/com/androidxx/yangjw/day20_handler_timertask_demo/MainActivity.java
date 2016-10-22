package com.androidxx.yangjw.day20_handler_timertask_demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerTask完成计时器的功能
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "androidxx";
    private Button mCodeBtn;
    private int second = 5;
    private boolean isRunning = true;

    private Timer timer = new Timer();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            second--;
            if (second > 0) {
                mCodeBtn.setText(second + "s后重发");
            } else if (second == 0) {
                isRunning = false;
                second = 60;
                mCodeBtn.setEnabled(true);
                mCodeBtn.setText("获取验证码");
            }



        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCodeBtn = (Button) findViewById(R.id.main_code_btn);
        mCodeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        isRunning = true;
        long time = System.currentTimeMillis() ;
        mCodeBtn.setEnabled(false);
        MyTimerTask myTimerTask = new MyTimerTask();
        timer.schedule(myTimerTask,new Date(time));
    }



    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            Log.i(TAG, "run: ");
            while (isRunning) {
                //运行在子线程中
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(1);
            }

        }
    }
}
