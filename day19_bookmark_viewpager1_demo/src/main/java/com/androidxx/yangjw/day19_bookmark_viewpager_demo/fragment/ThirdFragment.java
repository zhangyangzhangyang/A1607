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
public class ThirdFragment extends Fragment{



    public static ThirdFragment newInstance() {
        ThirdFragment firstFragment = new ThirdFragment();
        return firstFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_fragment_layout, container, false);
        return view;
    }
}
