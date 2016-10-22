package com.example.day26_helloword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * service
 * stared类型的
 * 1.创建一个类，extends service
 * 2.在xml中注册
 * 3.启动service
 * 如果执行多次startService方法，同一个service是不会重新创建的，仅仅导致onStartCommand方法执行多次
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View view){
        //启动service
        Intent intent = new Intent(this,Myservice.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束service，，当不在使用service时一定要将其结束
        Intent intent = new Intent(this,Myservice.class);
        stopService(intent);
    }
}
