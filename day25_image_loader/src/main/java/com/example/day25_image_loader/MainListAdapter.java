package com.example.day25_image_loader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by 张样 on 2016/10/12.
 */
public class MainListAdapter extends BaseAdapter{
    private List<String> datas;
    private Context context;


    public MainListAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
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
        ImageView imageView = (ImageView) convertView;
        if(imageView == null){
            imageView = new ImageView(context);
        }
        String path = datas.get(position);
        ImageLoader.load(context,path,imageView);
        return imageView;
    }
}
