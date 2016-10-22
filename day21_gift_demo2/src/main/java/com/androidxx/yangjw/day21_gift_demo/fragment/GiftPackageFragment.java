package com.androidxx.yangjw.day21_gift_demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.yangjw.day21_gift_demo.R;
import com.androidxx.yangjw.day21_gift_demo.adapter.ConvertBaseAdapter;
import com.androidxx.yangjw.day21_gift_demo.adapter.GiftListAdapter;
import com.androidxx.yangjw.day21_gift_demo.http.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjw on 2016/10/8.
 */
public class GiftPackageFragment extends BaseFragment implements ConvertBaseAdapter.IViewHolder{


    private static final String TAG = "androidxx";
    private ListView mListView;
    private List<String> datas = new ArrayList<>();
    private GiftListAdapter mGiftListAdapter;
    private ConvertBaseAdapter convertBaseAdapter;
    private BannerAdapter bannerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift_package_view, container, false);
        initView(view);
        loadData();
        return view;
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.gift_package_lv);
//        mGiftListAdapter = new GiftListAdapter(datas,mContext);
//        mListView.setAdapter(mGiftListAdapter);
        initHeaderView();
        convertBaseAdapter = new ConvertBaseAdapter(this);
        convertBaseAdapter.setDatas(datas);
        mListView.setAdapter(convertBaseAdapter);
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.header_view, null);
        mListView.addHeaderView(headerView);
        ViewPager mViewPager = (ViewPager) headerView.findViewById(R.id.header_view_pager);
        bannerAdapter = new BannerAdapter();
        mViewPager.setAdapter(bannerAdapter);
    }


    class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(R.mipmap.ic_launcher);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    private void loadData() {
        HttpUtils.request("http://www.1688wan.com/majax.action?method=getGiftList", true, "pageno=1",
                new HttpUtils.ICallBack() {
                    @Override
                    public void success(String result) {
                        Log.i(TAG, "success: -----" + result);
                        parseJson(result);
                    }
                });
    }


    private void parseJson(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String gname = jsonObject1.getString("gname");
                datas.add(gname);
            }

            convertBaseAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gift_package_lv_item, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTitleTxt.setText(datas.get(position));
        return convertView;
    }

    class ViewHolder {
        public TextView mTitleTxt;
        public ViewHolder(View view) {
            view.setTag(this);
            mTitleTxt = (TextView) view.findViewById(R.id.gift_package_lv_item_title_tv);
        }
    }
}
