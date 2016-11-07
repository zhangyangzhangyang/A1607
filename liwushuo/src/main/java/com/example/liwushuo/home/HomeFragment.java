package com.example.liwushuo.home;

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

import com.example.liwushuo.R;
import com.example.liwushuo.home.home_1.Home_1_Fragment;
import com.example.liwushuo.home.home_2.Home_10_Fragment;
import com.example.liwushuo.home.home_2.Home_11_Fragment;
import com.example.liwushuo.home.home_2.Home_12_Fragment;
import com.example.liwushuo.home.home_2.Home_13_Fragment;
import com.example.liwushuo.home.home_2.Home_14_Fragment;
import com.example.liwushuo.home.home_2.Home_2_Fragment;
import com.example.liwushuo.home.home_2.Home_3_Fragment;
import com.example.liwushuo.home.home_2.Home_4_Fragment;
import com.example.liwushuo.home.home_2.Home_5_Fragment;
import com.example.liwushuo.home.home_2.Home_6_Fragment;
import com.example.liwushuo.home.home_2.Home_7_Fragment;
import com.example.liwushuo.home.home_2.Home_8_Fragment;
import com.example.liwushuo.home.home_2.Home_9_Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 张样 on 2016/11/4.
 */
public class HomeFragment extends Fragment {

    private List<Fragment> fragmentList = new ArrayList<>();
    @BindView(R.id.fragment_home_tl)
    TabLayout mTabLayout;
    @BindView(R.id.fragment_home_vp)
    ViewPager mViewPager;
    private MyAdapter myAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        mTableLayout = (TabLayout) view.findViewById(R.id.fragment_home_tl);
        ButterKnife.bind(this,view);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        addTabLayoutData();
        initDatas();
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
    private void addTabLayoutData(){
        mTabLayout.addTab(mTabLayout.newTab().setText("精选"));
        mTabLayout.addTab(mTabLayout.newTab().setText("送女票"));
        mTabLayout.addTab(mTabLayout.newTab().setText("海淘"));
        mTabLayout.addTab(mTabLayout.newTab().setText("创意生活"));
        mTabLayout.addTab(mTabLayout.newTab().setText("送基友"));
        mTabLayout.addTab(mTabLayout.newTab().setText("送爸妈"));
        mTabLayout.addTab(mTabLayout.newTab().setText("送同事"));
        mTabLayout.addTab(mTabLayout.newTab().setText("设计感"));
        mTabLayout.addTab(mTabLayout.newTab().setText("文艺风"));
        mTabLayout.addTab(mTabLayout.newTab().setText("奇葩搞怪"));
        mTabLayout.addTab(mTabLayout.newTab().setText("科技范"));
        mTabLayout.addTab(mTabLayout.newTab().setText("数码"));
        mTabLayout.addTab(mTabLayout.newTab().setText("萌萌哒"));
        mTabLayout.addTab(mTabLayout.newTab().setText("爱读书"));
    }

    private void initDatas() {
        Home_1_Fragment home_1_fragment = new Home_1_Fragment();
        Home_2_Fragment Home_2_fragment  = new Home_2_Fragment();
        Home_3_Fragment home_3_fragment = new Home_3_Fragment();
        Home_4_Fragment home_4_fragment = new Home_4_Fragment();
        Home_5_Fragment home_5_fragment = new Home_5_Fragment();
        Home_6_Fragment home_6_fragment = new Home_6_Fragment();
        Home_7_Fragment home_7_fragment = new Home_7_Fragment();
        Home_8_Fragment home_8_fragment = new Home_8_Fragment();
        Home_9_Fragment home_9_fragment = new Home_9_Fragment();
        Home_10_Fragment home_10_fragment = new Home_10_Fragment();
        Home_11_Fragment home_11_fragment = new Home_11_Fragment();
        Home_12_Fragment home_12_fragment = new Home_12_Fragment();
        Home_13_Fragment home_13_fragment = new Home_13_Fragment();
        Home_14_Fragment home_14_fragment = new Home_14_Fragment();
        fragmentList.add(home_1_fragment);
        fragmentList.add(Home_2_fragment);
        fragmentList.add(home_3_fragment);
        fragmentList.add(home_4_fragment);
        fragmentList.add(home_5_fragment);
        fragmentList.add(home_6_fragment);
        fragmentList.add(home_7_fragment);
        fragmentList.add(home_8_fragment);
        fragmentList.add(home_9_fragment);
        fragmentList.add(home_10_fragment);
        fragmentList.add(home_11_fragment);
        fragmentList.add(home_12_fragment);
        fragmentList.add(home_13_fragment);
        fragmentList.add(home_14_fragment);
    }
    class MyAdapter extends FragmentPagerAdapter {
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
