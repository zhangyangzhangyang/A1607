package com.android.a1000phone.chengling.national_day_homework.tesefargment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.a1000phone.chengling.national_day_homework.R;
import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengling on 2016/10/7.
 */
public class TeseFargmentOne extends Fragment {
    private String PATH = "http://www.1688wan.com/majax.action?method=bdxqs&pageNo=0";
    private ListView mListView;
    private List<Tese_Bean> teseOneBeen = new ArrayList<>();
    private Context mContext;
    private TeseOneAdapter teseOneAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tese_main_list_view,container,false);
        mListView = (ListView)view.findViewById(R.id.tese_two_main_list_view);
        teseOneAdapter = new TeseOneAdapter();
        mListView.setAdapter(teseOneAdapter);
        AsyncTaskTool.load(PATH).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                paeseJson(result);
            }
        });
        return view;
    }

    private void paeseJson(String result){
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String iconurl = jsonObj.getString("iconurl");
                String name = jsonObj.getString("name");
                String addtime = jsonObj.getString("addtime");
                String sid = jsonObj.getString("sid");
                Tese_Bean bean = new Tese_Bean(iconurl,name,addtime,sid);
                teseOneBeen.add(bean);
            }
            teseOneAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }



    class TeseOneAdapter extends BaseAdapter {

        private static final String PREFIX = "http://www.1688wan.com";
        private LayoutInflater mLayoutInflater;

        public TeseOneAdapter() {
            super();
            this.mLayoutInflater = LayoutInflater.from(getContext());
        }
        @Override
        public int getCount() {
            return teseOneBeen == null ? 0 : teseOneBeen.size();
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
                view = mLayoutInflater.inflate(R.layout.tese_layout,parent,false);
                viewHolder = new ViewHolder(view);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }
            Tese_Bean bean = teseOneBeen.get(position);
            ImageAsyncLoader.load(PREFIX+bean.getPath(),viewHolder.mImageView).execute();
            viewHolder.mTitleTxt.setText(bean.getTitle());
            viewHolder.mTimeTxt.setText(bean.getTime());
            return view;
        }
        class  ViewHolder{
            public ImageView mImageView;
            public TextView mTitleTxt;
            public TextView mTimeTxt;
            public ViewHolder(View view){
                view.setTag(this);
                mImageView = (ImageView) view.findViewById(R.id.tese_one_image);
                mTitleTxt = (TextView) view.findViewById(R.id.tese_one_name_txt);
                mTimeTxt = (TextView) view.findViewById(R.id.tese_one_time_txt);
            }
        }
    }
}
