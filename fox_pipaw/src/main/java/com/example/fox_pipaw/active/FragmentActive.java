package com.example.fox_pipaw.active;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fox_pipaw.Bean.ActiveBean;
import com.example.fox_pipaw.R;
import com.example.fox_pipaw.http.AsyncTaskTool;
import com.example.fox_pipaw.http.HttpUrl;
import com.example.fox_pipaw.http.ImageAsyncLoader;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/10/23.
 */
public class FragmentActive extends Fragment {
    private ListView mActiveContentListView;
    private List<ActiveBean.DataBean> data = new ArrayList<>();
    private ActiveAdapter activeAdapter;
    public static final String TAG = "android++";
    private ViewPager mViewPager;
    private HeaderAdepter headerAdepter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_main_active, container, false);
        mActiveContentListView = (ListView) view.findViewById(R.id.active_content_list);
        View view1 = inflater.inflate(R.layout.active_viewpager, mActiveContentListView, false);
        mViewPager = (ViewPager)view1. findViewById(R.id.active_header_viewpager);

        datas();
        activeAdapter = new ActiveAdapter();
        headerAdepter = new HeaderAdepter();
        mViewPager.setAdapter(headerAdepter);
        mActiveContentListView.addHeaderView(view1);
        mActiveContentListView.setAdapter(activeAdapter);

        return view;
    }

    private void datas(){
        AsyncTaskTool.load(HttpUrl.activeMainUrl).execute(new AsyncTaskTool.IMyCallback() {


            @Override
            public void success(String result) {
//                Log.i(TAG, "success: "+"start++++");
                Gson gson = new Gson();
                ActiveBean activeBean = gson.fromJson(result, ActiveBean.class);
                List<ActiveBean.DataBean> info = activeBean.getData();
                for (int i = 0; i < info.size(); i++) {
                    ActiveBean.DataBean dataBean = info.get(i);
                    String end_time = dataBean.getEnd_time();
                    String logo = dataBean.getLogo();
                    String title = dataBean.getTitle();
                    int supports = dataBean.getSupports();
                    int comments = dataBean.getComments();
                    data.add(dataBean);
//                    Log.i(TAG, "success: "+"end+++++");
                }
                activeAdapter.notifyDataSetChanged();
            }
        });
    }

    class ActiveAdapter extends BaseAdapter{
        @Override
        public int getCount() {
//            Log.i(TAG, "getCount: "+data.size());
            return data == null ? 0 : data.size();
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
            ViewHouder viewHouder = null;
            if(view == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.active_content_list_details, parent, false);
                viewHouder = new ViewHouder(view);
                }else{
                viewHouder = (ViewHouder) view.getTag();
            }
            ActiveBean.DataBean dataBean = data.get(position);
            viewHouder.mTimeTxt.setText(dataBean.getEnd_time());
            ImageAsyncLoader.load(dataBean.getLogo(),viewHouder.mImageview).execute();
            viewHouder.mTitleTxt.setText(dataBean.getTitle());
            viewHouder.mSupportBtn.setText("点赞"+dataBean.getSupports());
            viewHouder.mReplyBtn.setText("回复"+dataBean.getComments());
            viewHouder.mJoinBtn.setText("立即参加");
            return view;
        }
        class ViewHouder{
            private TextView mTimeTxt;
            private ImageView mImageview;
            private TextView mTitleTxt;
            private Button mSupportBtn;
            private Button mReplyBtn;
            private Button mJoinBtn;

            public ViewHouder(View view) {
                view.setTag(this);
                mTimeTxt =(TextView)view.findViewById(R.id.active_content_details_time_txt);
                mImageview= (ImageView) view.findViewById(R.id.active_content_details_image);
                mTitleTxt =(TextView)view.findViewById(R.id.active_content_details_title_txt);
                mSupportBtn= (Button) view.findViewById(R.id.active_content_details_support_btn);
                mReplyBtn= (Button) view.findViewById(R.id.active_content_details_reply_btn);
                mJoinBtn= (Button) view.findViewById(R.id.active_content_details_join_btn);
            }
        }
    }
    class HeaderAdepter extends PagerAdapter{
        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view1 = LayoutInflater.from(getContext()).inflate(R.layout.active_header_list_details, null);
            container.addView(view1);
            return view1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
