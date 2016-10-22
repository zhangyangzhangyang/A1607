package com.example.com.example.TeseFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;
import com.example.gift_bag_spirit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/10/5.
 */
public class TeseTwoFragment extends Fragment{
    private final String PATH= "http://www.1688wan.com/majax.action?method=getWeekll&pageNo=0";
    private ListView mListView;
    private List <TeseTwoBean> datas = new ArrayList<>();
    private Context mContext;
    private TeseTwoAdapter teseTwoAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tese_two_list_view, container,false);
        mListView = (ListView)view.findViewById(R.id.tese_two_lv);
        AsyncTaskTool.load(PATH).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                paeseJson(result);
            }
        });

        teseTwoAdapter = new TeseTwoAdapter();
        mListView.setAdapter(teseTwoAdapter);
        return view;
    }
    private void paeseJson(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String id = jsonObj.getString("id");
                String name = jsonObj.getString("name");
                String iconurl = jsonObj.getString("iconurl");
                TeseTwoBean bean = new TeseTwoBean(id,iconurl,name);
                datas.add(bean);
                Log.i("ANDROID+++++++", "paeseJson: "+name);
            }
            teseTwoAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
    class TeseTwoAdapter extends BaseAdapter{

        private static final String PREFIX = "http://www.1688wan.com";
        private LayoutInflater mLayoutInflater;

        public TeseTwoAdapter() {
            super();
            this.mLayoutInflater = LayoutInflater.from(mContext);
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
            ViewHolder viewHolder = null;
            if (view == null){
                view = mLayoutInflater.inflate(R.layout.tese_two_list_view_details,parent,false);
                viewHolder = new ViewHolder(view);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }
            TeseTwoBean bean = datas.get(position);
            ImageAsyncLoader.load(PREFIX + bean.getPath(),viewHolder.mImageView).execute();
            viewHolder.mTitleTxt.setText(bean.getName());
            return view;
        }
        class ViewHolder{
            public ImageView mImageView;
            public TextView mTitleTxt;
            public ViewHolder(View view) {
                view.setTag(this);
                mImageView = (ImageView) view.findViewById(R.id.tese_two_img);
                mTitleTxt = (TextView) view.findViewById(R.id.tese_two_title_lv);
            }
        }
    }
}
