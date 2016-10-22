package com.androidxx.yangjw.day19_bookmark_viewpager_demo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.androidxx.yangjw.day19_bookmark_viewpager_demo.adapter.MenuAdapter;
import com.androidxx.yangjw.day19_bookmark_viewpager_demo.fragment.FirstFragment;
import com.androidxx.yangjw.day19_bookmark_viewpager_demo.fragment.FourthFragment;
import com.androidxx.yangjw.day19_bookmark_viewpager_demo.fragment.SecondFragment;
import com.androidxx.yangjw.day19_bookmark_viewpager_demo.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * ViewPager+RadioGroup书签导航
 * added by yangjingwen at 2016.09.29 for bookmark
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private ViewPager mViewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private MenuAdapter menuAdapter;
    private RadioGroup mMenuRadioGroup;
    private RadioButton mCurrentBtn;

    /**
     * modified by yangjingwen at 2016.09.30 for free
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.main_view_pager);
        mMenuRadioGroup = (RadioGroup) findViewById(R.id.main_menu_rg);
        initDatas();
        menuAdapter = new MenuAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(menuAdapter);
        initListener();
    }

    private void initListener() {
        mMenuRadioGroup.check(R.id.main_menu_item_1);
        mCurrentBtn = (RadioButton) mMenuRadioGroup.getChildAt(0);
        mCurrentBtn.setText("菜单");
        mMenuRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i(TAG, "onCheckedChanged: checkedId=" + checkedId);
                switch (checkedId) {
                    case R.id.main_menu_item_1:
                        mViewPager.setCurrentItem(0);
                        contrlMenu(0);
                        break;
                    case R.id.main_menu_item_2:
                        Log.i(TAG, "------22222--mCurrentBtn=" + mCurrentBtn);

                        mViewPager.setCurrentItem(1);
                        contrlMenu(1);
                        break;
                    case R.id.main_menu_item_3:
                        mViewPager.setCurrentItem(2);
                        contrlMenu(2);
                        break;
                    case R.id.main_menu_item_4:
                        mViewPager.setCurrentItem(3);
                        contrlMenu(3);
                        break;
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected: ");
                switch (position) {
                    case 0:
                        mMenuRadioGroup.check(R.id.main_menu_item_1);
                        break;
                    case 1:
                        Log.i(TAG, "onPageSelected: ------");
                        mMenuRadioGroup.check(R.id.main_menu_item_2);
                        break;
                    case 2:
                        mMenuRadioGroup.check(R.id.main_menu_item_3);
                        break;
                    case 3:
                        mMenuRadioGroup.check(R.id.main_menu_item_4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void contrlMenu(int index) {
        if (mCurrentBtn != null) {
            mCurrentBtn.setText("");
        }
        if (index == 3) {
            index = 4;
        }
        mCurrentBtn = (RadioButton) mMenuRadioGroup.getChildAt(index);
        mCurrentBtn.setText("菜单");


    }



    private void initDatas() {
        fragments.add(FirstFragment.newInstance());
        fragments.add(SecondFragment.newInstance());
        fragments.add(ThirdFragment.newInstance());
        fragments.add(FourthFragment.newInstance());
    }


}
