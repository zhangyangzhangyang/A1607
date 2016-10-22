package com.cxk.menufragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chenxiaokang.commonlibrarytwo.AsyncTaskToolTwo;
import com.chenxiaokang.commonlibrarytwo.ImageAsyncLoader;
import com.cxk.OneFragment.GiftBean;
import com.cxk.OneFragment.GiftMessageActivity;
import com.cxk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */
public class OneFragment extends Fragment {
    public static OneFragment newInstance() {
        Bundle args = new Bundle();
        OneFragment fragment = new OneFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private List<GiftBean> datas = new ArrayList<GiftBean>();
    private ListView mListView;
    private MyAdapter mAdapter;

    private ViewPager mViewPager;
    private List<String> imagePaths = new ArrayList<>();
    private BannerAdapter bannerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.one_fragment, container,false);
        mListView=(ListView)view.findViewById(R.id.one_fragment_list_view);
        mAdapter = new MyAdapter();
        View headerView = inflater.inflate(R.layout.head_view, null);

        mViewPager = (ViewPager)headerView.findViewById(R.id.head_view);
        bannerAdapter = new BannerAdapter();
        mViewPager.setAdapter(bannerAdapter);
        mListView.addHeaderView(headerView);
        mListView.setAdapter(mAdapter);

        AsyncTaskToolTwo.load("http://www.1688wan.com/majax.action?method=getGiftList")
                .post("pageno=1")
                .execute(new AsyncTaskToolTwo.IMyCallback(){
                    @Override
                    public void success(String result) {
                        success1(result);
                        success2( result);
                    }
                });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),GiftMessageActivity.class);
                GiftBean giftBean = datas.get(position);
                String id1 = giftBean.getId();
                intent.putExtra("gid",id1);
                Log.i("aandroid", "onItemClick: "+id1);
                startActivity(intent);
            }
        });
        return view;
    }

    private void success1(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("ad");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject adJsonObj = jsonArray.getJSONObject(i);
                String iconurl = adJsonObj.getString("iconurl");
                imagePaths.add(iconurl);
            }
            bannerAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imagePaths == null ? 0 : imagePaths.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position){
            ImageView imageView = new ImageView(getContext());
            container.addView(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String path = imagePaths.get(position);
            String fullPath = "http://www.1688wan.com" + path;
            ImageAsyncLoader.load(fullPath,imageView).execute();
            return imageView;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
    public void success2(String result) {

//        Gson gson = new Gson();
//        GiftBean2 bean2 = gson.fromJson(result,GiftBean2.class);
//        List<GiftBean2.AdBean> ad = bean2.getAd();
//        List<GiftBean2.ListBean> list = bean2.getList();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray listArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject jsonObj = listArray.getJSONObject(i);
                String id= jsonObj.getString("id");
                String path = jsonObj.getString("iconurl");
                String gamaname= jsonObj.getString("gname");
                String giftname= jsonObj.getString("giftname");
                String left=jsonObj.getString("number");
                String time = jsonObj.getString("addtime");
                GiftBean giftBean=new GiftBean(id,path, gamaname, giftname, left, time);
                datas.add(giftBean);
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
                view = mLayoutInflater.inflate(R.layout.one_item, parent, false);
                mViewHolder = new ViewHolder(view);
            } else {
                mViewHolder = (ViewHolder) view.getTag();
            }

            GiftBean giftBean = datas.get(position);
            mViewHolder.mGamename.setText(giftBean.getGamename());
            mViewHolder.mGiftname.setText(giftBean.getGiftname());
            mViewHolder.mLeft.setText("剩余：" + giftBean.getLeft());
            mViewHolder.mTime.setText("时间:" + giftBean.getTime());
            ImageAsyncLoader.load(PREFIX + giftBean.getPath(), mViewHolder.mImageView).execute();
//            mViewHolder.button.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent=new Intent(getActivity(),GiftMessageActivity.class);
//                    String str = giftBean.getId();
//                    intent.putExtra("gid",str);
//                    startActivity(intent);
//                }
//            });
            return view;
        }
        class ViewHolder {
            public ImageView mImageView;
            public TextView mGamename;
            public TextView mGiftname;
            public TextView mLeft;
            public TextView mTime;
            public Button button;
            public ViewHolder(View view) {
                view.setTag(this);
                mImageView = (ImageView) view.findViewById(R.id.item_image_view);
                mGamename = (TextView) view.findViewById(R.id.item_text_view_gamaname);
                mGiftname = (TextView) view.findViewById(R.id.item_text_view_giftname);
                mLeft = (TextView) view.findViewById(R.id.item_text_view_left);
                mTime = (TextView) view.findViewById(R.id.item_text_view_time);
                button = (Button) view.findViewById(R.id.button1);
            }
        }
    }
}