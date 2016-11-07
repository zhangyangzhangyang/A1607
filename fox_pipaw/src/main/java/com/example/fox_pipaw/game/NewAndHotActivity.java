package com.example.fox_pipaw.game;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.example.fox_pipaw.R;

public class NewAndHotActivity extends AppCompatActivity {

    private TableLayout mTabLayout;
    private ViewPager mViewPager;
    private NewAndHotFragment newAndHotFragment;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_and_hot);
        initView();
    }

    private void initView() {
        supportFragmentManager = getSupportFragmentManager();
        newAndHotFragment = new NewAndHotFragment();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.new_and_hot_fragment,newAndHotFragment);
        fragmentTransaction.show(newAndHotFragment);
        fragmentTransaction.commit();
    }
}
