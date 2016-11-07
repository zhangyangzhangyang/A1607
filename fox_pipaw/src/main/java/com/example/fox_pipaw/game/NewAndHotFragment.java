package com.example.fox_pipaw.game;

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

import com.example.fox_pipaw.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/10/27.
 */
public class NewAndHotFragment extends Fragment {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private List<Fragment> fragmentList = new ArrayList<>();
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_and_hot, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.new_and_hot_tl);
        mViewPager = (ViewPager) view.findViewById(R.id.new_and_hot_vp);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText("推荐"));
        mTabLayout.addTab(mTabLayout.newTab().setText("最热"));
        mTabLayout.addTab(mTabLayout.newTab().setText("最新"));
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
        NewFragemt newFragemt = new NewFragemt();
        HotFragemt hotFragemt = new HotFragemt();
        RecommendFragment recommendFragment = new RecommendFragment();
        fragmentList.add(recommendFragment);
        fragmentList.add(hotFragemt);
        fragmentList.add(newFragemt);
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
            return fragmentList == null ? 0 :fragmentList.size();
        }
    }
}
