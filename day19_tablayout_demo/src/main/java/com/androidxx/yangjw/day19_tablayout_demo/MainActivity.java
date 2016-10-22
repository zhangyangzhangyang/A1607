package com.androidxx.yangjw.day19_tablayout_demo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPager mViewPager;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.main_view_pager);
        //平分TabLayout
//        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //可以滚动
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.addTab(mTabLayout.newTab().setText("菜单1").setIcon(R.mipmap.ic_launcher));
        mTabLayout.addTab(mTabLayout.newTab().setText("菜单2").setIcon(R.mipmap.ic_launcher));
        mTabLayout.addTab(mTabLayout.newTab().setText("菜单3").setIcon(R.mipmap.ic_launcher));
        mTabLayout.addTab(mTabLayout.newTab().setText("菜单4").setIcon(R.mipmap.ic_launcher));
        mTabLayout.addTab(mTabLayout.newTab().setText("菜单5").setIcon(R.mipmap.ic_launcher));
        mTabLayout.addTab(mTabLayout.newTab().setText("菜单5").setIcon(R.mipmap.ic_launcher));
        mTabLayout.addTab(mTabLayout.newTab().setText("菜单5").setIcon(R.mipmap.ic_launcher));
        mTabLayout.addTab(mTabLayout.newTab().setText("菜单5").setIcon(R.mipmap.ic_launcher));
        initData();
        myAdapter = new MyAdapter(getSupportFragmentManager());
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
    }

    private void initData() {
        fragmentList.add(MyFragment.newInstance("第一个界面"));
        fragmentList.add(MyFragment.newInstance("第二个界面"));
        fragmentList.add(MyFragment.newInstance("第三个界面"));
        fragmentList.add(MyFragment.newInstance("第四个界面"));
        fragmentList.add(MyFragment.newInstance("第五个界面"));
        fragmentList.add(MyFragment.newInstance("第五个界面"));
        fragmentList.add(MyFragment.newInstance("第五个界面"));
        fragmentList.add(MyFragment.newInstance("第五个界面"));
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
