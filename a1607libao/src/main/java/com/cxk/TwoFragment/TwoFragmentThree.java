package com.cxk.TwoFragment;

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

import com.chenxiaokang.commonlibrarytwo.AsyncTaskToolTwo;

import com.chenxiaokang.commonlibrarytwo.ImageAsyncLoader;
import com.cxk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */
public class TwoFragmentThree extends Fragment implements AsyncTaskToolTwo.IMyCallback{
    private List<GiftBean_2> datas2 = new ArrayList<GiftBean_2>();
    private ListView mListView_2;
    private MyAdapter2 mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.two_fragment3,null);
        mListView_2=(ListView)view.findViewById(R.id.two_fragment3_list_view);
        mAdapter = new MyAdapter2();
        mListView_2.setAdapter(mAdapter);
        AsyncTaskToolTwo.load("http://www.1688wan.com/majax.action?method=getWebfutureTest")
                .post("pageno=0")
                .execute(this);

        return view;
    }
    public void success(String result) {
        Log.i("====",""+result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray listArray = jsonObject.getJSONArray("info");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject jsonObj = listArray.getJSONObject(i);
                String path = jsonObj.getString("iconurl");
                String gname= jsonObj.getString("gname");
                Log.i("====",""+gname);
                String operators= jsonObj.getString("operators");
                String starttime = jsonObj.getString("teststarttime");
                GiftBean_2 giftBean_2=new GiftBean_2(path,gname,operators,starttime);
                datas2.add(giftBean_2);
            }
            mAdapter.notifyDataSetChanged();
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

            return datas2 == null ? 0 : datas2.size();
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
                view = mLayoutInflater.inflate(R.layout.two_item2, parent, false);
                mViewHolder = new ViewHolder(view);
            } else {
                mViewHolder = (ViewHolder) view.getTag();
            }
            GiftBean_2 giftBean_2 = datas2.get(position);
            mViewHolder.mGamename2.setText(giftBean_2.getGname());
            mViewHolder.mOperators.setText(giftBean_2.getOperators());
            mViewHolder. mStartTime.setText(giftBean_2.getStarttime());
            ImageAsyncLoader.load(PREFIX+giftBean_2.getPath(), mViewHolder.mImageView).execute();
            return view;
        }
    }
    class ViewHolder {
        public ImageView mImageView;
        public TextView mGamename2;
        public TextView mOperators;
        public TextView mStartTime;
        public ViewHolder(View view) {
            view.setTag(this);
            mImageView = (ImageView) view.findViewById(R.id.two_item2_image_view);
            mGamename2 = (TextView) view.findViewById(R.id.two_item2_text_gname);
            mOperators = (TextView) view.findViewById(R.id.two_item2_text_operators);
            mStartTime = (TextView) view.findViewById(R.id.two_item2_text_teststarttime);
        }
    }
}



