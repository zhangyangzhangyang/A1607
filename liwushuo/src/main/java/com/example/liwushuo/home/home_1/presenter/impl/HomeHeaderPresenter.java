package com.example.liwushuo.home.home_1.presenter.impl;

import com.example.liwushuo.home.home_1.module.HomeHeaderBean;
import com.example.liwushuo.home.home_1.module.IHomeHeaderModul;
import com.example.liwushuo.home.home_1.module.impl.HomeHeaderModul;
import com.example.liwushuo.home.home_1.presenter.IHomeHeaderPresenter;
import com.example.liwushuo.home.home_1.presenter.callback.Callback;
import com.example.liwushuo.home.home_1.view.IHomeHeaderView;

/**
 * Created by 张样 on 2016/11/6.
 */
public class HomeHeaderPresenter implements Callback, IHomeHeaderPresenter {
    private IHomeHeaderModul modul;
    private IHomeHeaderView view;

    public HomeHeaderPresenter(IHomeHeaderView view) {
        this.modul = new HomeHeaderModul();
        this.view = view;

    }

    @Override
    public void success(HomeHeaderBean homeHeaderBean) {
        this.view.setResult(homeHeaderBean);
    }

    @Override
    public void queryHeader() {
        this.modul.queryHeader(this);
    }
}
