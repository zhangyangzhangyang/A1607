package com.androidxx.yangjw.day19_bookmark_viewpager_demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yangjw on 2016/9/29.
 */
public class MenuAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public MenuAdapter(FragmentManager fm) {
        super(fm);
    }

    public MenuAdapter(FragmentManager fm,List<Fragment> data) {
        this(fm);
        this.fragmentList = data;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }
}
