package com.example.day31_mvp_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import preserter.GiftPresenter;
import preserter.IGiftPresenter;
import view.IGiftView;

public class MainActivity extends AppCompatActivity implements IGiftView {

    private ListView mListView;
    private List<String> datas = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private IGiftPresenter giftPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.main_list_view);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        mListView.setAdapter(adapter);
        giftPresenter = new GiftPresenter(this);
        giftPresenter.queryGiftList();

    }


    @Override
    public void refreshAdapter(List<String> datas) {
        this.datas.addAll(datas);
        this.adapter.notifyDataSetChanged();
    }
}
