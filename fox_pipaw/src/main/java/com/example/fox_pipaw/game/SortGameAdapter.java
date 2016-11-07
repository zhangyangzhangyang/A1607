package com.example.fox_pipaw.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fox_pipaw.Bean.BookBean;
import com.example.fox_pipaw.R;
import com.example.fox_pipaw.http.ImageAsyncLoader;

import java.util.List;

/**
 * Created by 张样 on 2016/10/27.
 */
public class SortGameAdapter extends BaseAdapter {
    private List<BookBean> datas;
    private Context mContext;

    public SortGameAdapter(List<BookBean> datas, Context mContext) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.sort_view, parent, false);
            viewHoudler = new ViewHoudler(view);
        }else{
            viewHoudler = (ViewHoudler) view.getTag();
        }
//        if( position % 4 == 0){
//            viewHoudler.viewEmpty.setVisibility(View.VISIBLE);
//        }
//        else {
//            viewHoudler.viewEmpty.setVisibility(View.GONE);
//        }
        BookBean bookBean = datas.get(position);
        ImageAsyncLoader.load(bookBean.getImgPath(),viewHoudler.imageView).execute();
        viewHoudler.mTitleTxt.setText(bookBean.getName());
        return view;
    }
    class ViewHoudler{
        public ImageView imageView;
        public TextView mTitleTxt;
//        public View viewEmpty;

        public ViewHoudler(View view) {
            view.setTag(this);
            imageView= (ImageView)view. findViewById(R.id.sort_game_list_image);
            mTitleTxt= (TextView)view. findViewById(R.id.sort_game_list_title_txt);
//            viewEmpty = view.findViewById(R.id.sort_game_list_view);
        }
    }
}
