package com.androidxx.yangjw.day30_pulltorefresh_demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView mPullListView;
    private List<String> datas = new ArrayList<>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //刷新完成
            mPullListView.onRefreshComplete();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPullListView = (PullToRefreshListView) findViewById(R.id.pull_list_view);
        loadDatas();
        mPullListView.setMode(PullToRefreshBase.Mode.BOTH);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        mPullListView.setAdapter(adapter);
        //配置刷新监听
        mPullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            /**
             * 刷新
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                datas.clear();
                for (int i = 0; i < 20; i++) {
                    datas.add("芙蓉姐姐" + i);
                }
                adapter.notifyDataSetChanged();
                mHandler.sendEmptyMessageDelayed(1,2000);
            }

            /**
             * 加载更多
             * @param refreshView
             */
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                for (int i = 0; i < 20; i++) {
                    datas.add("春哥" + i);
                }
                adapter.notifyDataSetChanged();
                mHandler.sendEmptyMessage(1);
            }
        });
    }

    private void loadDatas() {
        for (int i = 0; i < 20; i++) {
            datas.add("ITEM" + i);
        }
    }
}
