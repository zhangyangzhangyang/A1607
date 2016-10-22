package com.androidxx.yangjw.day19_tablayout_demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yangjw on 2016/9/29.
 */
public class MyFragment extends Fragment {
    public static final String KEY = "content";
    private Context context;

    public static MyFragment newInstance(String content) {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,content);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(context);
        textView.setText(getArguments().getString(KEY));
        return textView;
    }
}
