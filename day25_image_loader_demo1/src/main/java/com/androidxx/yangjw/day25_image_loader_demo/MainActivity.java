package com.androidxx.yangjw.day25_image_loader_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<String> paths = new ArrayList<>();
    private MainListAdapter mainListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.main_list_view);
        mainListAdapter = new MainListAdapter(paths,this);
        mListView.setAdapter(mainListAdapter);
    }

    private void initDatas() {
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201505/_1431668017101.jpg");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201605/_1463125483599.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201601/_1452243738874.jpg");
        paths.add("http://i3.72g.com/upload/201510/201510261436311061.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201609/_1474445209925.jpg");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201609/_1475201359049.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201609/_1473834636728.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201609/_1473225269861.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201606/_1466078455530.jpg");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201609/_1475049290778.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201604/_1460539173326.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201610/_1476068697973.jpg");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201607/_1468480737401.jpg");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DiskCacheTool.flush();
    }

}
