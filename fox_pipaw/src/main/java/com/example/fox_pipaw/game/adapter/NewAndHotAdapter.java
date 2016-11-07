package com.example.fox_pipaw.game.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fox_pipaw.Bean.NewAndHotBean;
import com.example.fox_pipaw.R;
import com.example.fox_pipaw.http.ImageAsyncLoader;

import java.util.List;

/**
 * Created by 张样 on 2016/10/27.
 */
public class NewAndHotAdapter extends BaseAdapter {
    private List<NewAndHotBean>datas;
    private Context mContext;

    public NewAndHotAdapter(List<NewAndHotBean> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHouder viewHouder = null;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.game_new_hot_details, parent, false);
            viewHouder = new ViewHouder(view);
        }else{
            viewHouder = (ViewHouder) view.getTag();
        }
        NewAndHotBean bean = datas.get(position);
        ImageAsyncLoader.load(bean.getLogo(),viewHouder.imageView).execute();
        viewHouder.mTitleTxt.setText(bean.getName());
        viewHouder.mdescTxt.setText(bean.getDescript());
        viewHouder.mSizeTxt.setText(bean.getType()+"|"+bean.getSize()+"|");
        viewHouder.mGiftTxt.setText(bean.getCount()+"种礼包|");
        viewHouder.mPeopleTxt.setText("人气："+bean.getVisits());
        return view;
    }
    class ViewHouder{
        public ImageView imageView;
        public TextView mTitleTxt;
        public TextView mdescTxt;
        public TextView mSizeTxt;
        public TextView mGiftTxt;
        public TextView mPeopleTxt;

        public ViewHouder(View view) {
            view.setTag(this);
            imageView = (ImageView) view.findViewById(R.id.game_new_hot_details_image);
            mTitleTxt = (TextView) view.findViewById(R.id.game_new_hot_details_title_txt);
            mdescTxt = (TextView) view.findViewById(R.id.game_new_hot_details_desc_txt);
            mSizeTxt = (TextView) view.findViewById(R.id.game_new_hot_details_size_txt);
            mGiftTxt = (TextView) view.findViewById(R.id.game_new_hot_details_gift_txt);
            mPeopleTxt = (TextView) view.findViewById(R.id.game_new_hot_details_peopleno_txt);
        }
    }
}
