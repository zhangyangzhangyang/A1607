package com.androidxx.yangjw.day25_image_loader_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by yangjw on 2016/10/12.
 */
public class MainListAdapter extends BaseAdapter {
    private List<String> datas ;
    private Context context;
    private LayoutInflater inflater;

    public MainListAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
//        ImageView imageView = (ImageView) convertView;
//        if (imageView == null) {
//            imageView = new ImageView(context);
//        }
//        //加载图片
//        String path = datas.get(position);
//        ImageLoader.load(path,imageView);
//        return imageView;
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.item_view,parent,false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        String path = datas.get(position);
        ImageLoader.load(context,path,viewHolder.imageView);
        return view;
    }

    class ViewHolder {
        public ImageView imageView;

        public ViewHolder(View view) {
            view.setTag(this);
            imageView = (ImageView) view.findViewById(R.id.item_image_view);

        }
    }
}
