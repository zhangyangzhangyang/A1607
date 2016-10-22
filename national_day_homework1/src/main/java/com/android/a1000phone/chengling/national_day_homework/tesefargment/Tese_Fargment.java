package com.android.a1000phone.chengling.national_day_homework.tesefargment;

import android.graphics.Color;
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
import android.widget.BaseAdapter;

import com.android.a1000phone.chengling.national_day_homework.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengling on 2016/9/30.
 */
public class Tese_Fargment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> datas = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private MyAdapter myAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tese_main,container,false);
        mTabLayout = (TabLayout)view.findViewById(R.id.tese_main_tab_layout);
        mViewPager = (ViewPager)view.findViewById(R.id.tese_main_view_pager);
        mTabLayout.setTabTextColors(Color.BLACK,Color.RED);
        mTabLayout.setSelectedTabIndicatorColor(Color.GREEN);
        initDatas();
        myAdapter = new MyAdapter(getChildFragmentManager());
        mViewPager.setAdapter(myAdapter);
//        mTabLayout.addTab(mTabLayout.newTab().setTag("暴打星期三"));
//        mTabLayout.addTab(mTabLayout.newTab().setTag("新游周刊"));
        mTabLayout.setupWithViewPager(mViewPager);
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
            public void onPageSelected(int position)      {
                mTabLayout.setScrollPosition(position,0,true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    private void initDatas() {
        Fragment fragmentOne = new TeseFargmentOne();
        Fragment fragmentTwo = new TestFargmentTwo();
        datas.add(fragmentOne);
        datas.add(fragmentTwo);
        titles.add("暴打星期三");
        titles.add("新游周刊");
    }
    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
