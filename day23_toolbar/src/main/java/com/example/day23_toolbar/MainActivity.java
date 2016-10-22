package com.example.day23_toolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBar = (Toolbar) findViewById(R.id.main_tool_bar);
        //使用ToolBar替代actionbar作为标题栏
        setSupportActionBar(mToolBar);
        setTitle("AAAA");
        mToolBar.setSubtitle("zzzzzz");
        mToolBar.setNavigationIcon(R.mipmap.ic_launcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("菜单一");
        menu.add("菜单二");
        menu.add("菜单三");
        menu.add("菜单四");
        return true;
    }
}
