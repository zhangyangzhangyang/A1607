package com.androidxx.yangjw.day23_actionbar_actionview_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * 显示action view的步骤
 * 1、创建一个Menu资源
 * 2、重写onCreateOptionMenu方法
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *
     * @param menu 系统自带选项菜单对象
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_one:
                Toast.makeText(MainActivity.this, "第一个菜单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_two:
                Toast.makeText(MainActivity.this, "第二个菜单", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
