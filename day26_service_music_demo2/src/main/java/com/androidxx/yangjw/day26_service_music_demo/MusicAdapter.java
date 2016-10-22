package com.androidxx.yangjw.day26_service_music_demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.androidxx.yangjw.day26_service_music_demo.service.MusicService;

/**
 * Created by yangjw on 2016/10/13.
 */
public class MusicAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflator;

    public MusicAdapter(Context context) {
        this.context = context;
        inflator = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 10;
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
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null) {
            view = inflator.inflate(R.layout.item_view,parent,false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }

    class ViewHolder implements View.OnClickListener{
        public Button mPlayBtn;

        public ViewHolder(View view) {
            view.setTag(this);
            this.mPlayBtn = (Button) view.findViewById(R.id.item_music_play_btn);
            this.mPlayBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //播放音乐
            Intent intent = new Intent(context, MusicService.class);
            context.startService(intent);
        }
    }
}
