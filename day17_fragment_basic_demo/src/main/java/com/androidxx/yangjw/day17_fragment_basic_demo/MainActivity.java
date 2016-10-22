package com.androidxx.yangjw.day17_fragment_basic_demo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Fragment的动态使用方式
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mOneBtn;
    private Button mTwoBtn;
    private Button mThreeBtn;
    private FragmentManager mFragmentManager;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mOneBtn = (Button) findViewById(R.id.main_index_one_btn);
        mTwoBtn = (Button) findViewById(R.id.main_index_two_btn);
        mThreeBtn = (Button) findViewById(R.id.main_index_three_btn);
        mOneBtn.setOnClickListener(this);
        mTwoBtn.setOnClickListener(this);
        mThreeBtn.setOnClickListener(this);
        //1、获取FragmentManager对象
        //Fragment是由FragmentManager进行管理的
        mFragmentManager = getSupportFragmentManager();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_index_one_btn:
                switchOneFragment();
                break;
            case R.id.main_index_two_btn:
                switchTwoFragment();
                break;
            case R.id.main_index_three_btn:
                switchThreeFragment();
                break;
        }
    }

    private void switchOneFragment() {

        //2、通过FragmentManager开启一个事务
        //事务的特征：1、原子性，2、一致性，3、持久性，4、隔离性
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        //3、通过事务对象将Fragment加载到布局中
        //参数1：Activity布局文件中的FrameLayout的id，
        //参数2：需要显示到屏幕上的fragment对象
        oneFragment = new OneFragment();
        //最终效果就是将oneFragment加载到FrameLayout布局文件中
        transaction.replace(R.id.main_fragment_content_layout,oneFragment);
        transaction.addToBackStack(null);
        //4、提交事务
        transaction.commit();
    }

    private void switchTwoFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        twoFragment = new TwoFragment();
//        transaction.replace(R.id.main_fragment_content_layout,twoFragment);
        transaction.add(R.id.main_fragment_content_layout,twoFragment);
        transaction.addToBackStack(null);
        transaction.remove(oneFragment);
        transaction.commit();
    }


    private void switchThreeFragment() {
//        FragmentTransaction transaction = mFragmentManager.beginTransaction();
//        threeFragment = new ThreeFragment();
//        transaction.replace(R.id.main_fragment_content_layout,threeFragment);
//        transaction.commit();
        Intent intent = new Intent(this,IndexActivity.class);
        startActivity(intent);
    }
}
