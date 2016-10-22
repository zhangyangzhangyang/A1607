package com.androidxx.yangjw.day21_gift_demo.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangjw on 2016/10/8.
 */
public class ConvertBaseAdapter extends AbstarctBaseAdapter {

    private IViewHolder viewHolder;

    public ConvertBaseAdapter(IViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return viewHolder.getView(position,convertView,parent);
    }


    public interface IViewHolder {

        View getView(int position, View convertView, ViewGroup parent);
    }
}
