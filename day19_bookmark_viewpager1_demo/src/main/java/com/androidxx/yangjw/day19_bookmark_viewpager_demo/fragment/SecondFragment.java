package com.androidxx.yangjw.day19_bookmark_viewpager_demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidxx.yangjw.day19_bookmark_viewpager_demo.R;

/**
 * Created by yangjw on 2016/9/29.
 */
public class SecondFragment extends Fragment{



    public static SecondFragment newInstance() {
        SecondFragment firstFragment = new SecondFragment();
        return firstFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment_layout, container, false);
        return view;
    }
}
