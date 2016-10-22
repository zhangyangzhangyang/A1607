package com.example.gift_bag_spirit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.com.example.TeseFragment.TeseFragment;
import com.example.com.example.kaifuFragment.KaifuFragment;
import com.example.hot.HotFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup mRadioGroup;
    private FragmentManager mFragmentManager;

    private GiftBagFragment giftBagFragment;
    private KaifuFragment kaifuFragment;
    private HotFragment hotFragment;
    private TeseFragment teseFragment;

    private Fragment mCurrentShowFrament;
    private RadioButton mGiftBagBtn;
    private Toolbar mToolBarGiftBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBarGiftBar = (Toolbar) findViewById(R.id.gift_bag_header_tool_bar);
        setSupportActionBar(mToolBarGiftBar);
        initView();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup_rg);
        mFragmentManager =getSupportFragmentManager();
        giftBagFragment = new GiftBagFragment();
        kaifuFragment = new KaifuFragment();
        hotFragment = new HotFragment();
        teseFragment = new TeseFragment();
        mRadioGroup.check(R.id.gift_bag_rbn);
        checkedFragment(giftBagFragment);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.gift_bag_rbn:
                checkedFragment(giftBagFragment);

                 break;
            case R.id.kaifu_rbn:
                checkedFragment(kaifuFragment);
                break;
            case R.id.hot_rbn:
                checkedFragment(hotFragment);
                break;
            case R.id.tese_rbn:
                checkedFragment(teseFragment);
                break;
        }
    }
    /**
     * 通过add、show方法显示OneFragment
     *
     */
    private void checkedFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if(mCurrentShowFrament != null){
            fragmentTransaction.hide(mCurrentShowFrament);
        }
        if(!fragment.isAdded()){
            fragmentTransaction.add(R.id.list_view_frame,fragment);
        }else{
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFrament = fragment;

    }
}
