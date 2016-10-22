package com.cxk.menufragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.chenxiaokang.commonlibrarytwo.AsyncTaskToolTwo;
import com.chenxiaokang.commonlibrarytwo.ImageAsyncLoader;
import com.cxk.R;
import com.cxk.ThreeFragment.GiftBean3;
import com.cxk.ThreeFragment.GiftBean_3;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */
public class ThreeFragment extends Fragment {

    private List<GiftBean3> datas = new ArrayList<GiftBean3>();
    private ListView mListView;
    private MyAdapter mAdapter;

    private List<GiftBean_3> datas2 = new ArrayList<GiftBean_3>();
    private GridView mGridView;
    private MyAdapter2 mAdapter2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.three_fragment, null);
        mListView = (ListView) view.findViewById(R.id.three_fragment_list_view);
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);
        mGridView = (GridView) view.findViewById(R.id.three_fragment_gride_view);
        mAdapter2 = new MyAdapter2();
        mGridView.setAdapter(mAdapter2);
        AsyncTaskToolTwo.load("http://www.1688wan.com//majax.action?method=hotpushForAndroid")
                .execute(new AsyncTaskToolTwo.IMyCallback() {
                    @Override
                    public void success(String result) {
                        success1(result);
                        success2( result);
                    }
                });
        return view;
    }
    public void success1(String result) {

        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject listObject = jsonObject.getJSONObject("info");
            JSONArray listArray= listObject.getJSONArray("push1");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject jsonObj = listArray.getJSONObject(i);
                String path = jsonObj.getString("logo");
                String name = jsonObj.getString("name");
                String typename = jsonObj.getString("typename");
                String size = jsonObj.getString("size");
                String clicks = jsonObj.getString("clicks");
                GiftBean3 giftBean3 = new GiftBean3(path, name, typename, size, clicks);
                datas.add(giftBean3);
            }
            mAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class MyAdapter extends BaseAdapter {
        private static final String PREFIX = "http://www.1688wan.com";

        private LayoutInflater mLayoutInflater;

        public MyAdapter() {
            super();
            mLayoutInflater = LayoutInflater.from(getContext());
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return datas == null ? 0 : datas.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder mViewHolder = null;
            if (view == null) {
                view = mLayoutInflater.inflate(R.layout.item3_view, parent, false);
                mViewHolder = new ViewHolder(view);
            } else {
                mViewHolder = (ViewHolder) view.getTag();
            }

            GiftBean3 giftBean3 = datas.get(position);
            mViewHolder.mGamename.setText(giftBean3.getName());
            mViewHolder.mTypename.setText("类型：" + giftBean3.getTypename());
            mViewHolder.mSize.setText("大小：" + giftBean3.getSize());
            mViewHolder.mClicks.setText(giftBean3.getClicks() + "人在玩");
            ImageAsyncLoader.load(PREFIX + giftBean3.getPath(), mViewHolder.mImageView).execute();
            return view;
        }

        class ViewHolder {
            public ImageView mImageView;
            public TextView mGamename;
            public TextView mTypename;
            public TextView mSize;
            public TextView mClicks;

            public ViewHolder(View view) {
                view.setTag(this);
                mImageView = (ImageView) view.findViewById(R.id.item3_image_view);
                mGamename = (TextView) view.findViewById(R.id.item3_text_view_gamaname);
                mTypename = (TextView) view.findViewById(R.id.item3_text_view_typename);
                mSize = (TextView) view.findViewById(R.id.item3_text_view_size);
                mClicks = (TextView) view.findViewById(R.id.item3_text_view_clicks);

            }
        }
    }
    public void success2(String result) {

        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject listObject = jsonObject.getJSONObject("info");
            JSONArray listArray= listObject.getJSONArray("push2");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject jsonObj = listArray.getJSONObject(i);
                String path = jsonObj.getString("logo");
                String name = jsonObj.getString("name");
                GiftBean_3 giftBean_3 = new GiftBean_3(path, name);
                datas2.add(giftBean_3);
            }
            mAdapter2.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class MyAdapter2 extends BaseAdapter {
        private static final String PREFIX = "http://www.1688wan.com";

        private LayoutInflater mLayoutInflater;

        public MyAdapter2() {
            super();
            mLayoutInflater = LayoutInflater.from(getContext());
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return datas == null ? 0 : datas2.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder mViewHolder = null;
            if (view == null) {
                view = mLayoutInflater.inflate(R.layout.item3_view2, parent, false);
                mViewHolder = new ViewHolder(view);
            } else {
                mViewHolder = (ViewHolder) view.getTag();
            }

            GiftBean_3 giftBean_3 = datas2.get(position);
            mViewHolder.mGamename.setText(giftBean_3.getName());

            Picasso.with(getActivity()).load(PREFIX + giftBean_3.getPath()).into(mViewHolder.mImageView);
            return view;
        }

        class ViewHolder {
            public ImageView mImageView;
            public TextView mGamename;


            public ViewHolder(View view) {
                view.setTag(this);
                mImageView = (ImageView) view.findViewById(R.id.item3_image_view2);
                mGamename = (TextView) view.findViewById(R.id.item3_text_view2);
            }
        }
    }
}