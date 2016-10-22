package com.android.a1000phone.chengling.national_day_homework;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.android.a1000phone.chengling.national_day_homework.frist_page_fargment.GiftBagFragment;
import com.android.a1000phone.chengling.national_day_homework.frist_page_fargment.Host_Fargment;
import com.android.a1000phone.chengling.national_day_homework.frist_page_fargment.Kaifu_Fargment;
import com.android.a1000phone.chengling.national_day_homework.frist_page_fargment.Libao_Fargment;
import com.android.a1000phone.chengling.national_day_homework.frist_page_fargment.Tese_Fargment;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    //botton Fargment
    private RadioGroup mRadioGroup;
    private FragmentManager mfragmentManager;
    private GiftBagFragment libao_fargment;
    private Kaifu_Fargment kaifu_fargment;
    private Host_Fargment host_fargment;
    private Tese_Fargment tese_fargment;
    private Fragment mCurrentShowFrament;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.botton_radioGroup_rg);
        mfragmentManager = getSupportFragmentManager();
        libao_fargment = new GiftBagFragment();
        kaifu_fargment = new Kaifu_Fargment();
        host_fargment = new Host_Fargment();
        tese_fargment = new Tese_Fargment();
        mRadioGroup.check(R.id.botton_libao_rbn);
        checkedFragment(libao_fargment);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.botton_libao_rbn :
                checkedFragment(libao_fargment);
                break;
            case R.id.botton_kaifu_rbn :
                checkedFragment(kaifu_fargment);
                break;
            case R.id.botton_host_rbn :
                checkedFragment(host_fargment);
                break;
            case R.id.botton_tese_rbn :
                checkedFragment(tese_fargment);
                break;
        }
    }

    private void checkedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mfragmentManager.beginTransaction();
        if (mCurrentShowFrament != null){
            fragmentTransaction.hide(mCurrentShowFrament);
        }
        if (!fragment.isAdded()){
            fragmentTransaction.add(R.id.list_view_frame,fragment);
        }
        else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFrament = fragment;
    }
}
