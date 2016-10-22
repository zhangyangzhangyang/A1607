package com.androidxx.yangjw.day18_viewpager_indicatior_demo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 带滚动指示器的ViewPager
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mVIewPager;
    private List<Integer> datas = new ArrayList<>();
    private BannerAdapter bannerAdapter;
    private LinearLayout mIndicatorLayout;
    private View mLastShowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVIewPager = (ViewPager) findViewById(R.id.main_view_pager);
        mIndicatorLayout = (LinearLayout) findViewById(R.id.main_indicator_layout);

        int childCount = mIndicatorLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == 0) {
                mLastShowView = mIndicatorLayout.getChildAt(i);
                mIndicatorLayout.getChildAt(i).setEnabled(true);
            } else {
                mIndicatorLayout.getChildAt(i).setEnabled(false);
            }
        }
        initData();
        bannerAdapter = new BannerAdapter();
        mVIewPager.setAdapter(bannerAdapter);
        //设置ViewPager的监听事件
        mVIewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * ViewPager正在滚动
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * item被选中时
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                View view = mIndicatorLayout.getChildAt(position);
                view.setEnabled(true);
                if (mLastShowView != null) {
                    mLastShowView.setEnabled(false);
                }
                mLastShowView = view;
            }

            /**
             * 滚动状态发生变化
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        datas.add(R.drawable.page_1);
        datas.add(R.drawable.pager_2);
        datas.add(R.drawable.pager_3);
        datas.add(R.drawable.pager_4);
    }

    class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(datas.get(position));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
