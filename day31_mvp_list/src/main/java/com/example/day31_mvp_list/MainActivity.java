package com.example.day31_mvp_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.day31_mvp_list.model.GameBean;
import com.example.day31_mvp_list.presenter.IGamePresenter;
import com.example.day31_mvp_list.ui.MyAdapter;
import com.example.day31_mvp_list.view.IGameView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements IGameView{


    private ListView mListView;
    private List<GameBean.InfoBean>datas = new ArrayList<>();
    private MyAdapter myAdapter;
    private IGamePresenter gamePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.main_list_view);
        myAdapter = new MyAdapter(datas,this);
        mListView.setAdapter(myAdapter);
//        gamePresenter.queryGameList();
    }


    @Override
    public void setListDatas(GameBean bean) {
       datas.addAll(bean.getInfo());
        myAdapter.notifyDataSetChanged();
    }
}
