package com.example.hot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;
import com.example.gift_bag_spirit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/9/27.
 */
public class HotFragment extends Fragment {
    private ListView mListView;
    private  final String  PATH = "http://www.1688wan.com//majax.action?method=hotpushForAndroid";
    private List<HotBean> hotBeen= new ArrayList<>();
    private Context mContext;
    private HotAdapter hotAdapter;
    private List<String> datas = new ArrayList<>();
    private GridView mGridView;
    private MyAdapter myAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_main_view, container,false);
        mListView = (ListView) view.findViewById(R.id.hot_list_frameLayout);
        mGridView = (GridView) view.findViewById(R.id.hot_grid_view);
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext,HotDetailsActivity.class);
                HotBean bean = hotBeen.get(position);
                String hotId = bean.getId();
                intent.putExtra("hotId", hotId);
                Log.i("zhangynagyang", "onItemClick: "+ hotId);
                startActivity(intent);
//                Toast.makeText(mContext,"zhangyangzan====",Toast.LENGTH_SHORT).show();
            }
        });
        AsyncTaskTool .load(PATH).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                paeseJson(result);
                paeseJsonGrid(result);
            }


        });
        myAdapter = new MyAdapter();
        mGridView.setAdapter(myAdapter);

        hotAdapter = new HotAdapter();
        mListView.setAdapter(hotAdapter);
        Log.i("ANDROID", "onCreateView: +++++++++++++++++++++++++++++++++++++");
        return view;
    }
    private void paeseJsonGrid(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject jsonObject1 = jsonObject.getJSONObject("info");
            JSONArray jsonArray = jsonObject1.getJSONArray("push2");
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String logo = jsonObj.getString("logo");
                Log.i("ANDROID", "onCreateView: +++++++++++++++++++++++++++++++++++++"+logo);
                datas.add(logo);
            }
            myAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return datas == null ? 0 :datas.size();
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
            if(view == null){
                view = LayoutInflater.from(mContext).inflate(R.layout.hot_grid_view_details,parent,false);
                viewHolder = new ViewHolder();
                viewHolder.mImageVeiw = (ImageView) view.findViewById(R.id.hot_grid_view_img);
                view.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)view.getTag();
            }
            String imagePath = datas.get(position);
//            ImageAsyncLoader.load(PREFIX+bean.getPath(),viewHolder.mImageView).execute();
            ImageAsyncLoader.load("http://www.1688wan.com/"+ imagePath,viewHolder.mImageVeiw).execute();
            return view;
        }
        class ViewHolder{
            public ImageView mImageVeiw;
        }
    }

    private void paeseJson(String result) {
        Log.i("ANDROID", "paeseJson: -----------------------------");
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject infoObe = jsonObject.getJSONObject("info");
            JSONArray jsonArray = infoObe.getJSONArray("push1");
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String id = jsonObj.getString("id");
                String logo = jsonObj.getString("logo");
                String name = jsonObj.getString("name");
                String typename = jsonObj.getString("typename");
                String size = jsonObj.getString("size");
                String clicks = jsonObj.getString("clicks");
                Log.i("ANDROID++++++", "paeseJson: "+name);
                HotBean bean = new HotBean(id,logo,name,typename,size,clicks);
                hotBeen.add(bean);
            }
              hotAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
    class HotAdapter extends BaseAdapter {

        private static final String PREFIX = "http://www.1688wan.com/";
        private LayoutInflater mLayoutInflater;

        public HotAdapter() {
            super();
            this.mLayoutInflater = LayoutInflater.from(mContext);
        }
        @Override
        public int getCount() {
            return hotBeen == null ? 0 : hotBeen.size();
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
                view = mLayoutInflater.inflate(R.layout.hot_list_view,parent,false);
                viewHolder = new ViewHolder(view);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }
            HotBean bean = hotBeen.get(position);
            ImageAsyncLoader.load(PREFIX+bean.getPath(),viewHolder.mImageView).execute();
            viewHolder.mTitleTxt.setText(bean.getGname());
            viewHolder.mGiftNameTxt.setText("类型："+bean.getTypename());
            viewHolder.mSizeTxt.setText("大小："+bean.getSize());
            viewHolder.mPeopleTxt.setText(bean.getPeople()+"人在玩");
            return view;
        }

        class ViewHolder {
            public ImageView mImageView;
            public TextView mTitleTxt;
            public TextView mGiftNameTxt;
            public TextView mSizeTxt;
            public TextView mPeopleTxt;

            public ViewHolder(View view) {
                view.setTag(this);
                mImageView = (ImageView) view.findViewById(R.id.hot_image_view);
                mTitleTxt = (TextView) view.findViewById(R.id.hot_title_txt);
                mGiftNameTxt = (TextView) view.findViewById(R.id.hot_role_txt);
                mSizeTxt = (TextView) view.findViewById(R.id.hot_number_txt);
                mPeopleTxt = (TextView) view.findViewById(R.id.hot_people_number_txt);
            }
        }
    }
}
