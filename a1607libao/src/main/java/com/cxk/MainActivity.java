package com.cxk;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.cxk.menufragment.FourFragment;
import com.cxk.menufragment.OneFragment;
import com.cxk.menufragment.ThreeFragment;
import com.cxk.menufragment.TwoFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup mMenuRg;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentShowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        mMenuRg = (RadioGroup) findViewById(R.id.main_radiogroup);
        mFragmentManager = getSupportFragmentManager();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fourFragment=new FourFragment();
        mMenuRg.check(R.id.main_radio_one);
        checkedFragment(oneFragment);
        mMenuRg.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId){
        switch (checkedId) {
            case R.id.main_radio_one:
                checkedFragment(oneFragment);
                break;
            case R.id.main_radio_two:
                checkedFragment(twoFragment);
                break;
            case R.id.main_radio_three:
                checkedFragment(threeFragment);
                break;
            case R.id.main_radio_four:
                checkedFragment(fourFragment);
                break;
        }
    }

    /**
     * 通过add、show方法显示Fragment
     */
    private void checkedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (mCurrentShowFragment != null) {
            fragmentTransaction.hide(mCurrentShowFragment);
        }
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.main_fragment, fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFragment = fragment;
    }
}