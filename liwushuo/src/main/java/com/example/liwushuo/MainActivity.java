package com.example.liwushuo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.liwushuo.category.CategoryFragment;
import com.example.liwushuo.gift.GiftFragment;
import com.example.liwushuo.home.HomeFragment;
import com.example.liwushuo.profile.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.main_radiogroup)
    RadioGroup mRadioGroup;
    private FragmentManager supportFragmentManager;
    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;
    private GiftFragment giftFragment;
    private ProfileFragment profileFragment;
    private Fragment mCurrentShowFrament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        ButterKnife.bind(this);
        supportFragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        categoryFragment = new CategoryFragment();
        giftFragment = new GiftFragment();
        profileFragment = new ProfileFragment();
        mRadioGroup.check(R.id.main_1_home_rb);
        checkFragment(homeFragment);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_1_home_rb:
                checkFragment(homeFragment);
                break;
            case R.id.main_2_gift_rb:
                checkFragment(giftFragment);
                break;
            case R.id.main_3_category_rb:
                checkFragment(categoryFragment);
                break;
            case R.id.main_4_profile_rb:
                checkFragment(profileFragment);
                break;
        }

    }
    /**
     * 通过add、show方法显示OneFragment
     * @param fragment
     */
    private void checkFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (mCurrentShowFrament != null){
            fragmentTransaction.hide(mCurrentShowFrament);
        }
        if (!fragment.isAdded()){
            fragmentTransaction.add(R.id.main_fragment,fragment);
        }else{
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFrament = fragment;
    }
}
