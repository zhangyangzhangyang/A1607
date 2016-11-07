package com.example.fox_pipaw.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fox_pipaw.Bean.BookBean;
import com.example.fox_pipaw.R;
import com.example.fox_pipaw.http.AsyncTaskTool;
import com.example.fox_pipaw.http.HttpUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SortGameActivity extends AppCompatActivity {

    private ListView mSortListView;
    private List<BookBean>datas = new ArrayList<>();
    private SortGameAdapter sortGameAdapter;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_game);
        mSortListView = (ListView) findViewById(R.id.sort_game_list_view);
//        view = findViewById(R.id.sort_game_view);
//        if (datas.size() % 4 == 0){
//            view.setVisibility(View.VISIBLE);
//        }
        initDatas();
        sortGameAdapter = new SortGameAdapter(datas,SortGameActivity.this);
        mSortListView.setAdapter(sortGameAdapter);
        mSortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SortGameActivity.this,NewAndHotActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDatas() {
        AsyncTaskTool.load(HttpUrl.sortBtnUrl).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                parse(result);
            }
        });
    }
    private void parse(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String pic = jsonObject.getString("pic");
                BookBean bookBean = new BookBean(id, pic, name);
                datas.add(bookBean);
            }
            sortGameAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
