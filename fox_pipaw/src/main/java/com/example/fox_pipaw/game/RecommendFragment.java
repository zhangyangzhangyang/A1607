package com.example.fox_pipaw.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fox_pipaw.Bean.NewAndHotBean;
import com.example.fox_pipaw.R;
import com.example.fox_pipaw.game.adapter.NewAndHotAdapter;
import com.example.fox_pipaw.game.preseter.GamePresenter;
import com.example.fox_pipaw.game.view.IGameView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/10/27.
 */
public class RecommendFragment extends Fragment implements IGameView{
    private ListView mListView;
    private List<NewAndHotBean>datas = new ArrayList<>();
    private NewAndHotAdapter newAndHotAdapter;
    private GamePresenter gamePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);
        mListView = (ListView) view.findViewById(R.id.list_view_item);
        newAndHotAdapter = new NewAndHotAdapter(datas,getContext());
        mListView.setAdapter(newAndHotAdapter);
        gamePresenter = new GamePresenter(this);
        gamePresenter.queryGameList();
        return view;
    }


    @Override
    public void refreshAdapter(List<NewAndHotBean> datas) {
        this.datas.addAll(datas);
        this.newAndHotAdapter.notifyDataSetChanged();
    }
}
