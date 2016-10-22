package com.android.a1000phone.chengling.national_day_homework.tesefargment;



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
public class TestFargmentTwo extends Fragment {
    private ListView mListView;
    private String PATH = "http://www.1688wan.com/majax.action?method=getWeekll&pageNo=0";
    private List<Tese_Two_Bean> datas = new ArrayList<>();
    private MyTeseAdapter myTeseAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tese_main_list_view,container,false);
        mListView = (ListView)view.findViewById(R.id.tese_two_main_list_view);
        myTeseAdapter = new MyTeseAdapter();
        mListView.setAdapter(myTeseAdapter);
        AsyncTaskTool.load(PATH).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                initDatas(result);
            }
        });
        return view;
    }

    private void initDatas(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String iconurl = jsonObject1.getString("iconurl");
                String name = jsonObject1.getString("name");
                Tese_Two_Bean bean = new Tese_Two_Bean(null,iconurl,name,null);
                datas.add(bean);
            }
                myTeseAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    class MyTeseAdapter extends BaseAdapter{
        private String PATHFIX = "http://www.1688wan.com";




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
                view = LayoutInflater.from(getContext()).inflate(R.layout.tese_layout_kaice,parent,false);
//                view = mLayoutInflater.inflate(R.layout.tese_layout,parent,false);
                viewHolder = new ViewHolder(view);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Tese_Two_Bean bean1 = datas.get(position);
            ImageAsyncLoader.load(PATHFIX+bean1.getIconurl(),viewHolder.mImageView).execute();
            viewHolder.mTitle.setText(bean1.getName());
            return view;
        }
    }
    class ViewHolder{
        private ImageView mImageView;
        private TextView mTitle;
        public ViewHolder(View view) {
            view.setTag(this);
            mImageView= (ImageView) view.findViewById(R.id.tese_kaice_iv);
            mTitle = (TextView) view.findViewById(R.id.tese_kaice_tv);
        }
    }
}
