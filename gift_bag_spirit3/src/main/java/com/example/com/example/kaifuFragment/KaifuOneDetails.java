package com.example.com.example.kaifuFragment;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;
import com.example.gift_bag_spirit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KaifuOneDetails extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mTitle;
    private TextView mType;
    private TextView mDescription;
    private List<String> datas = new ArrayList<>();
    private ViewPager mViewPager;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaifu_one_details2);
        initView();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
//        Log.i(TAG, "onCreate: ");
        AsyncTaskTool.load("http://www.1688wan.com/majax.action?method=getAppInfo").post("id="+id).execute(new AsyncTaskTool.IMyCallback() {            public static final String TAG = "android++";

            @Override
            public void success(String result) {
                Log.i(TAG, "success: "+result);
                parseJson( result);
                
            }
        });
        myAdapter = new MyAdapter();
        mViewPager.setAdapter(myAdapter);
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.kaifu_two_list5_img);
        mTitle = (TextView) findViewById(R.id.kaifu_two_list_title5_txt);
        mType = (TextView) findViewById(R.id.kaifu_two_list_operate5_txt);
        mDescription = (TextView) findViewById(R.id.kaifu_game_details_txt);
        mViewPager = (ViewPager) findViewById(R.id.kaifu_viewpager);
    }

    private void parseJson(String result){
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray imgJson = jsonObject.getJSONArray("img");
            int length = imgJson.length();
            for (int i = 0; i < length; i++) {
                JSONObject imgObj = imgJson.getJSONObject(i);
                String address = imgObj.getString("address");
                datas.add(address);

            }
            myAdapter.notifyDataSetChanged();
            JSONObject appJson = jsonObject.getJSONObject("app");
            String logo = appJson.getString("logo");
            String name = appJson.getString("name");
            String typename = appJson.getString("typename");
            String description = appJson.getString("description");
            ImageAsyncLoader.load("http://www.1688wan.com"+logo,mImageView).execute();
            mTitle.setText(name);
            mType.setText("类型："+typename);
            mDescription.setText(description);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getBaseContext());
            container.addView(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String path = datas.get(position);
            String imgPath =  "http://www.1688wan.com" + path;
            ImageAsyncLoader.load(imgPath,imageView).execute();
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
