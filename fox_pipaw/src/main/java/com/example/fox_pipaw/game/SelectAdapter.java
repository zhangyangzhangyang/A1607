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
public class SelectAdapter extends BaseAdapter {
    private static final String TAG = "android++";
    private List<GameTodayBean>datas;
    private Context mContext;

    public SelectAdapter(List<GameTodayBean> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
//        Log.i(TAG, "getCount: " + datas.size());
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
        GameTodayBean bean = datas.get(position);
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.game_select_details,parent,false);
            viewHoudler = new ViewHoudler(view);
        }else{
            viewHoudler = (ViewHoudler) view.getTag();
        }
        ImageAsyncLoader.load(bean.getLogo(),viewHoudler.imageView).execute();
        viewHoudler.mTitleTxt.setText(bean.getGame_name());
        viewHoudler.mTypeTxt.setText(bean.getType_name());
        viewHoudler.mPeopleNoTxt.setText(bean.getGame_visits());
        return view;
    }
    class ViewHoudler{
        public ImageView imageView;
        public TextView mTitleTxt;
        public TextView mTypeTxt;
        public TextView mPeopleNoTxt;
        public Button mLoadBtn;

        public ViewHoudler(View view) {
            view.setTag(this);
            imageView = (ImageView) view.findViewById(R.id.game_select_detials_image);
            mTitleTxt = (TextView) view.findViewById(R.id.game_select_detials_title_txt);
            mTypeTxt = (TextView) view.findViewById(R.id.game_select_detials_type_txt);
            mPeopleNoTxt = (TextView) view.findViewById(R.id.game_select_details_peopleno_txt);
            mLoadBtn = (Button) view.findViewById(R.id.game_select_details_btn);
        }
    }
}
