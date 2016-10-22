package com.example.day23_action_bar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        //通过Id查询指定的Menu的item
        MenuItem menuItem = menu.findItem(R.id.search_view);
        //获取Item中配置的ActionView对象
        SearchView searchView = (SearchView) menuItem.getActionView();
        //配置SearchView的监听事件
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             * 提交搜索输入框中的内容（开始搜索）
             * @param query
             * @return
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "提交", Toast.LENGTH_SHORT).show();
                return true;
            }

            /**
             * 当输入框中的文字发生变化的时候
             * @param newText 修改后的文字
             * @return
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, newText, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return true;
    }
}
