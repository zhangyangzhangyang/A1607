package com.example.fox_pipaw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.example.fox_pipaw.active.FragmentActive;
import com.example.fox_pipaw.book.FragmentBook;
import com.example.fox_pipaw.game.FragmentGame;
import com.example.fox_pipaw.gift.FragmentGift;
import com.example.fox_pipaw.user.FragmentUser;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup mRadioGroup;
    private FragmentManager supportFragmentManager;
    private FragmentActive fragmentActive;
    private FragmentBook fragmentBook;
    private FragmentGame fragmentGame;
    private FragmentGift fragmentGift;
    private FragmentUser fragmentUser;
    private Fragment mCurrentShowFrament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
        supportFragmentManager = getSupportFragmentManager();
        fragmentActive = new FragmentActive();
        fragmentBook = new FragmentBook();
        fragmentGame = new FragmentGame();
        fragmentGift = new FragmentGift();
        fragmentUser = new FragmentUser();
        mRadioGroup.check(R.id.game_image);
        checkFragment(fragmentGame);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.game_image:
                checkFragment(fragmentGame);
                break;
            case R.id.gift_image:
                checkFragment(fragmentGift);
                break;
            case R.id.active_image:
                checkFragment(fragmentActive);
                break;
            case R.id.book_image:
                checkFragment(fragmentBook);
                break;
            case R.id.user_image:
                checkFragment(fragmentUser);
                break;
        }
    }

    /**
     * 通过add、show方法显示OneFragment
     * @param fragment
     */
    private void checkFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if(mCurrentShowFrament != null){
            fragmentTransaction.hide(mCurrentShowFrament);
        }
        if (!fragment.isAdded()){
            fragmentTransaction.add(R.id.main_framelayout,fragment);
        }else{
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFrament = fragment;
    }
}
