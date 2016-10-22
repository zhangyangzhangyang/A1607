package com.androidxx.yangjw.day23_toolbar_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

/**
 * 介绍ToolBar的基本使用
 * 步骤：
 * 1、去掉actionbar（为了使用ToolBar去替代actionBar）
 *
 */
public class MainActivity extends AppCompatActivity {

    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBar = (Toolbar) findViewById(R.id.main_tool_bar);
        //使用ToolBar替代actionbar作为标题栏
        setSupportActionBar(mToolBar);
//        mToolBar.setTitle("AAAAA");
        setTitle("AAAAA");
        mToolBar.setSubtitle("xxxxx");
        mToolBar.setNavigationIcon(R.drawable.account_back);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("菜单1");
        return true;
    }
}
