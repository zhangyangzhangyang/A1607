package com.example.com.example.TeseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gift_bag_spirit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/9/27.
 */
public class TeseFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tese_main_view,container,false);
        mTabLayout =(TabLayout) view.findViewById(R.id.tese_tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.tese_view_pager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText("暴打星期三"));
        mTabLayout.addTab(mTabLayout.newTab().setText("新游周刊"));
        initData();
        myAdapter = new MyAdapter(getChildFragmentManager());
        mViewPager.setAdapter(myAdapter);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setScrollPosition(position,0,true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    private void initData() {
        Fragment teseOneFragment = new TeseOneFragment();
        Fragment teseTwoFragment = new TeseTwoFragment();
        fragmentList.add(teseOneFragment);
        fragmentList.add(teseTwoFragment);
    }

    class MyAdapter extends FragmentPagerAdapter{
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }
}
