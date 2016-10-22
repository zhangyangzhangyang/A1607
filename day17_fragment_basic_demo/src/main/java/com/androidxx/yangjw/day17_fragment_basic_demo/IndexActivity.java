package com.androidxx.yangjw.day17_fragment_basic_demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class IndexActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup mMenuRg;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FragmentManager mFragmentManager;

    private Fragment mCurrentShowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initView();
    }

    private void initView() {
        mMenuRg = (RadioGroup) findViewById(R.id.index_menu_rg);
        mFragmentManager = getSupportFragmentManager();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        mMenuRg.check(R.id.index_menu_one_rb);
        checkedFragment(oneFragment);
        mMenuRg.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.index_menu_one_rb:
                checkedFragment(oneFragment);
                break;
            case R.id.index_menu_two_rb:
                checkedFragment(twoFragment);
                break;
            case R.id.index_menu_three_rb:
                checkedFragment(threeFragment);
                break;
        }
    }

    /**
     * 通过add、show方法显示OneFragment
     *
     */
    private void checkedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (mCurrentShowFragment != null ) {
            fragmentTransaction.hide(mCurrentShowFragment);
        }
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.index_fragment_content_layout,fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFragment = fragment;
    }

//    private void checkedFragment(Fragment fragment) {
//        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        fragmentTransaction.hide(mCurrentShowFragment);
//        if (!fragment.isAdded()) {
//            fragmentTransaction.add(R.id.index_fragment_content_layout,fragment);
//        } else {
//            fragmentTransaction.show(fragment);
//        }
//        fragmentTransaction.commit();
//        mCurrentShowFragment = fragment;
//
//    }
}
