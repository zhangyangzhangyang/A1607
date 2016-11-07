package com.example.liwushuo.home.home_1.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.liwushuo.home.home_1.module.HomeHeaderBean;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by 张样 on 2016/11/6.
 */
public class HomeHeaderAdapter extends PagerAdapter {
    private List<HomeHeaderBean> dataBeen;
    private Context context;

    public HomeHeaderAdapter(List<HomeHeaderBean> dataBeen, Context context) {
        this.dataBeen = dataBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataBeen == null ? 0 :dataBeen.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        container.addView(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        HomeHeaderBean.DataBean data = dataBeen.get(0).getData();
        String image_url= data.getBanners().get(position).getImage_url();
//        String image_url = dataBeen.get(position).getBanners().get(position).getImage_url();
        Picasso.with(context).load(image_url).into(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
