package com.example.fox_pipaw.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fox_pipaw.Bean.GameTodayBean;
import com.example.fox_pipaw.R;
import com.example.fox_pipaw.http.ImageAsyncLoader;

import java.util.List;

/**
 * Created by 张样 on 2016/10/25.
 */
public class TodayAdapter extends BaseAdapter {
    private List<GameTodayBean> datas;
    private Context mContext;

    public TodayAdapter(List<GameTodayBean> datas,Context mContext) {
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
        ViewHoudler viewHoudler = null;
        if (view == null){
          view =LayoutInflater.from(mContext).inflate(R.layout.game_listview_details,parent,false);
            viewHoudler = new ViewHoudler(view);
        }{
            viewHoudler = (ViewHoudler) view.getTag();
        }
        GameTodayBean bean = datas.get(position);
        ImageAsyncLoader.load(bean.getLogo(),viewHoudler.imageView).execute();
        viewHoudler.mTitle.setText(bean.getGame_name());
        viewHoudler.mSize.setText(bean.getType_name()+"|"+bean.getSize()+"|");
        viewHoudler.mPeopleNum.setText("人气："+bean.getGame_visits());
        viewHoudler.mDescl.setText(bean.getDescl());
        return view;
    }
    class ViewHoudler{
        public ImageView imageView;
        public TextView mTitle;
        public TextView mSize;
        public TextView mPeopleNum;
        public TextView mDescl;
        public Button mBtn;

        public ViewHoudler(View view) {
            view.setTag(this);
            imageView = (ImageView) view.findViewById(R.id.game_listview_image);
            mTitle = (TextView) view.findViewById(R.id.game_listview_title_tv);
            mSize = (TextView) view.findViewById(R.id.game_listview_size_txt);
            mPeopleNum = (TextView) view.findViewById(R.id.game_listview_people_txt);
            mDescl = (TextView) view.findViewById(R.id.game_listview_descl_txt);
            mBtn = (Button) view.findViewById(R.id.game_listview_btn);
        }
    }
}
