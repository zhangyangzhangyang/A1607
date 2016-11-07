package com.example.liwushuo.home.home_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liwushuo.R;
import com.example.liwushuo.home.home_1.adapter.HomeHeaderAdapter;
import com.example.liwushuo.home.home_1.module.HomeHeaderBean;
import com.example.liwushuo.home.home_1.presenter.IHomeHeaderPresenter;
import com.example.liwushuo.home.home_1.presenter.impl.HomeHeaderPresenter;
import com.example.liwushuo.home.home_1.view.IHomeHeaderView;
import com.example.liwushuo.tool.CustomGridView;
import com.example.liwushuo.tool.CustomListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 张样 on 2016/11/6.
 */
public class Home_1_Fragment extends Fragment implements IHomeHeaderView {
    private List<HomeHeaderBean>dataBeen = new ArrayList<>();

    @BindView(R.id.home_1_view_vp)
    ViewPager mViewPager;
    @BindView(R.id.home_1_view_gv)
    CustomGridView customGridView;
    @BindView(R.id.home_1_view_lv)
    CustomListView customListView;
    private HomeHeaderAdapter homeHeaderAdapter;
    private IHomeHeaderPresenter homeHeaderPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_1_view,container,false);
        ButterKnife.bind(this,view);
        homeHeaderPresenter = new HomeHeaderPresenter(this);
        homeHeaderPresenter.queryHeader();
        homeHeaderAdapter = new HomeHeaderAdapter(dataBeen,getContext());
        mViewPager.setAdapter(homeHeaderAdapter);
        return view;
    }

    @Override
    public void setResult(HomeHeaderBean homeHeaderBean) {
        dataBeen.add(homeHeaderBean);
        homeHeaderAdapter.notifyDataSetChanged();
    }
}
