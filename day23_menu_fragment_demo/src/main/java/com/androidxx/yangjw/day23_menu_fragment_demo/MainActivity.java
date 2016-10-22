package com.androidxx.yangjw.day23_menu_fragment_demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RadioGroup;

import com.androidxx.yangjw.day23_menu_fragment_demo.fragment.BlankFragment;
import com.androidxx.yangjw.day23_menu_fragment_demo.fragment.SecondFragment;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private FragmentManager mFragmentManager;
    private BlankFragment blankFragment;
    private SecondFragment secondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        mRadioGroup = (RadioGroup) findViewById(R.id.menu_group_rg);
        blankFragment = BlankFragment.newInstance("","");
        secondFragment = SecondFragment.newInstance("","");
        setupLinstener();

    }

    private void setupLinstener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.menu_one_rb:
                        controlFragment(blankFragment);
                        break;
                    case R.id.menu_two_rb:
                        controlFragment(secondFragment);
                        break;
                }
            }
        });
    }

    private void controlFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content_layout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu,menu);
        return true;
    }
}
