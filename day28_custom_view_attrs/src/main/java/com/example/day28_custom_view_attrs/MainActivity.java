package com.example.day28_custom_view_attrs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 自定义属性步骤
 * 1.创建一个属性资源文件
 * 2.使用并加载属性资源文件
 * 3.查询属性对应的值
 * 4.将属性的值给相应的控件
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
