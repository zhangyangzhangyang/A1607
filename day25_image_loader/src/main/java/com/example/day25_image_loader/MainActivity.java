package com.example.day25_image_loader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> paths = new ArrayList<>();
    private ListView mListView;
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
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201505/_1431668017101.jpg");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201605/_1463125483599.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201609/_1474445209925.jpg");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201609/_1475201359049.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201609/_1473834636728.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201609/_1475201359049.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201505/_1431668017101.jpg");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201605/_1463125483599.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201505/_1431668017101.jpg");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201605/_1463125483599.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201609/_1474445209925.jpg");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201609/_1475201359049.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201609/_1473834636728.png");
        paths.add("http://www.1688wan.com/allimgs/img_iapp/201609/_1475201359049.png");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DiskCacheTool.flush();
    }
}
