package com.androidxx.yangjw.day20_handler_code_demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Handler完成验证码获取的时间倒数功能
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mCodeBtn;
    private int second = 60;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            second--;
            if (second == 0) {
                mCodeBtn.setEnabled(true);
                mCodeBtn.setText("获取验证码");
            }
            if (second > 0) {
                mCodeBtn.setText(second + "S后重发");
                this.sendEmptyMessageDelayed(1,1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCodeBtn = (Button) findViewById(R.id.main_get_code_btn);
        mCodeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mCodeBtn.setEnabled(false);
        mHandler.sendEmptyMessage(1);
    }
}
