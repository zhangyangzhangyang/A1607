package com.example.day26_media.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.day26_media.R;
import com.example.day26_media.service.MusicService;

/**
 * Created by 张样 on 2016/10/13.
 */
public class MusicAdapter extends BaseAdapter {

    private final LayoutInflater infator;
    private Context mContext;

    public MusicAdapter(Context mContext) {
        this.mContext = mContext;
        infator = LayoutInflater.from(mContext);
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
        ViewHoulder viewHoulder = null;
        if(view == null){
            view = infator.inflate(R.layout.item_view,parent,false);
            viewHoulder = new ViewHoulder(view);
        }     else {
            viewHoulder = (ViewHoulder) view.getTag();
        }

        return view;
    }
    class ViewHoulder implements View.OnClickListener{
        public Button mMusicbtn;

        public ViewHoulder(View view){
            view.setTag(this);
            this.mMusicbtn = (Button) view.findViewById(R.id.item_music_play_btn);
            this.mMusicbtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, MusicService.class);
            mContext.startService(intent);
        }
    }
}
