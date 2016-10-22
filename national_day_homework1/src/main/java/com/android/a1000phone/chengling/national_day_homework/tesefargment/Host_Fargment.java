package com.android.a1000phone.chengling.national_day_homework.tesefargment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
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
 * Created by chengling on 2016/9/30.
 */
public class Host_Fargment extends Fragment {
    private ListView mListView;
    private final String PATH = "http://www.1688wan.com//majax.action?method=hotpushForAndroid";
    List<Host_Bean> datas = new ArrayList<>();
    List<Host_Bean> datas2 = new ArrayList<>();
    private HotMyAdapter hotMyAdapter;
    private HotMyAdapter2 hotMyAdapter2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_main_view,null);
        mListView = (ListView) view.findViewById(R.id.frist_hot_list_view_lv);
        GridView gridView = (GridView) view.findViewById(R.id.second_hot_grid_view);
        hotMyAdapter = new HotMyAdapter();
        hotMyAdapter2 = new HotMyAdapter2();
        mListView.setAdapter(hotMyAdapter);
        gridView.setAdapter(hotMyAdapter2);
        AsyncTaskTool.load(PATH).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                initDatas(result);
                initDatas2(result);
            }
        });
        return view;
    }
    private void initDatas2(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject info = jsonObject.getJSONObject("info");
            JSONArray jsonArray = info.getJSONArray("push2");
            int len = jsonArray.length();
            for (int i = 0; i < len ; i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String logo = jsonObject1.getString("logo");
                String name = jsonObject1.getString("name");
                Host_Bean host_bean = new Host_Bean(id, logo, null, null, null, name);
                datas2.add(host_bean);
            }
            hotMyAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void initDatas(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject info = jsonObject.getJSONObject("info");
            JSONArray jsonArray = info.getJSONArray("push1");
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String logo = jsonObject1.getString("logo");
                String size = jsonObject1.getString("size");
                String clicks = jsonObject1.getString("clicks");
                String typename = jsonObject1.getString("typename");
                String name = jsonObject1.getString("name");
                Host_Bean host_bean = new Host_Bean(id, logo, size, clicks, typename, name);
                datas.add(host_bean);
            }
            hotMyAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
        class HotMyAdapter2 extends  BaseAdapter{
            private static final String PREFIX2 = "http://www.1688wan.com";
            @Override
            public int getCount() {
                return datas2 == null ? 0 : datas2.size();
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
                ViewHolder2 viewHolder2 = null;
                if (view == null){
                    view = LayoutInflater.from(getContext()).inflate(R.layout.hot_main_view_item_two,parent,false);
                    viewHolder2 = new ViewHolder2(view);
                }else {
                    viewHolder2 = (ViewHolder2) view.getTag();
                }
                Host_Bean bean2 = datas2.get(position);
                ImageAsyncLoader.load(PREFIX2+bean2.getLogo(),viewHolder2.mImageView).execute();
                viewHolder2.mTextView.setText(bean2.getName());
                return view;
            }
        }
        class HotMyAdapter extends BaseAdapter{
            private static final String PREFIX = "http://www.1688wan.com";

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
                    view = LayoutInflater.from(getContext()).inflate(R.layout.hot_main_view_item,parent,false);
                    viewHolder = new ViewHolder(view);
                }else {
                    viewHolder = (ViewHolder) view.getTag();
                }
                Host_Bean hostBean = datas.get(position);
                ImageAsyncLoader.load(PREFIX+hostBean.getLogo(),viewHolder.mImageView).execute();
                viewHolder.mtitle.setText(hostBean.getName());
                viewHolder.mNumber.setText(hostBean.getClicks());
                viewHolder.mSize.setText(hostBean.getSize());
                viewHolder.mType.setText(hostBean.getTypename());

                return view;
            }
        }
    class ViewHolder2{
        public ImageView mImageView;
        public TextView mTextView;
        public ViewHolder2(View view) {
            view.setTag(this);
            mImageView = (ImageView)view.findViewById(R.id.hot_image_view);
            mTextView = (TextView)view.findViewById(R.id.hot_text_view);
        }
    }

    class ViewHolder{
        public ImageView mImageView;
        public TextView mtitle;
        public TextView mType;
        public TextView mSize;
        public TextView mNumber;
        public ViewHolder(View view) {
            view.setTag(this);
            mImageView = (ImageView) view.findViewById(R.id.hot_one_list_img);
            mtitle = (TextView) view.findViewById(R.id.hot_one_list_title_txt);
            mType = (TextView) view.findViewById(R.id.hot_one_list_time_txt);
            mSize = (TextView) view.findViewById(R.id.hot_one_list_operate_txt);
            mNumber = (TextView) view.findViewById(R.id.hot_one_list_number);
        }
    }

}
