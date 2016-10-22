package com.androidxx.yangjw.day21_gift_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.yangjw.day21_gift_demo.R;

import java.util.List;

/**
 * Created by yangjw on 2016/10/8.
 */
public class GiftListAdapter extends BaseAdapter {

    private List<String> datas;
    private Context mContext;
    private LayoutInflater mInflater;

    public GiftListAdapter(List<String> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.gift_package_lv_item, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTitleTxt.setText(datas.get(position));
        return convertView;
    }

    class ViewHolder {
        public TextView mTitleTxt;
        public ViewHolder(View view) {
            view.setTag(this);
            mTitleTxt = (TextView) view.findViewById(R.id.gift_package_lv_item_title_tv);
        }
    }
}
