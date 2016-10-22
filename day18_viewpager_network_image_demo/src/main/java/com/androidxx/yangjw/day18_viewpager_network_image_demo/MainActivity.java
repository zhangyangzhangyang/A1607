package com.androidxx.yangjw.day18_viewpager_network_image_demo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 网络图片的加载
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<String> imagePaths = new ArrayList<>();
    private BannerAdapter bannerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.main_view_pager);
        bannerAdapter = new BannerAdapter();
        mViewPager.setAdapter(bannerAdapter);
        AsyncTaskTool.load("http://www.1688wan.com/majax.action?method=getGiftList")
                .post("pageno=1")
                .execute(new AsyncTaskTool.IMyCallback() {
                    @Override
                    public void success(String result) {
                        parseJson(result);
                    }
        });
    }

    private void parseJson(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("ad");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject adJsonObj = jsonArray.getJSONObject(i);
                String iconurl = adJsonObj.getString("iconurl");
                imagePaths.add(iconurl);
            }
            bannerAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imagePaths == null ? 0 : imagePaths.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(MainActivity.this);
            container.addView(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String path = imagePaths.get(position);
            String fullPath = "http://www.1688wan.com" + path;
            ImageAsyncLoader.load(fullPath,imageView).execute();
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}
