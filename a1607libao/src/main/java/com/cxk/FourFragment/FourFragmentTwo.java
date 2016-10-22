package com.cxk.FourFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.chenxiaokang.commonlibrarytwo.AsyncTaskToolTwo;

import com.chenxiaokang.commonlibrarytwo.ImageAsyncLoader;
import com.cxk.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
public class FourFragmentTwo extends Fragment implements AsyncTaskToolTwo.IMyCallback{
    private List<GiftBean4> datas = new ArrayList<GiftBean4>();
    private ListView mListView;
    private MyAdapter mAdapter;
    private RadioGroup radioGroup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.four_fragment2,null);
        mListView=(ListView)view.findViewById(R.id.four_fragment2_list_view);
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);
        AsyncTaskToolTwo.load("http://www.1688wan.com/majax.action?method=bdxqs&pageNo=0")
                .post("pageno=0")
                .execute(this);

        return view;
//		mListView.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position,
//					long id) {
//				Intent intent=new Intent(MainActivity.this,GiftActivity.class);
//				startActivity(intent);
//			}
//		});
    }
    public void success(String result) {

        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray listArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject jsonObj = listArray.getJSONObject(i);
                String path = jsonObj.getString("iconurl");
                String gamaname= jsonObj.getString("name");
                String time = jsonObj.getString("addtime");
                GiftBean4 giftBean4=new GiftBean4(path, gamaname,time);
                datas.add(giftBean4);
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
                view = mLayoutInflater.inflate(R.layout.four_item, parent, false);
                mViewHolder = new ViewHolder(view);
            } else {
                mViewHolder = (ViewHolder) view.getTag();
            }
            GiftBean4 giftBean4 = datas.get(position);
            mViewHolder.mGamename.setText(giftBean4.getName());
            mViewHolder.mTime.setText(giftBean4.getAddtime());
            ImageAsyncLoader.load(PREFIX+giftBean4.getPath(), mViewHolder.mImageView).execute();
            return view;
        }
    }
        class ViewHolder {
            public ImageView mImageView;
            public TextView mGamename;
            public TextView mTime;

            public ViewHolder(View view) {
                view.setTag(this);
                mImageView = (ImageView) view.findViewById(R.id.item4_image_view);
                mTime = (TextView) view.findViewById(R.id.item4_text_view_name);
                mGamename= (TextView) view.findViewById(R.id.item4_text_view_time);

            }
        }
    }

