package com.androidxx.yangjw.day21_gift_demo.adapter;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by yangjw on 2016/10/8.
 */
public abstract class AbstarctBaseAdapter extends BaseAdapter {

    protected List datas ;

    public void setDatas(List datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
