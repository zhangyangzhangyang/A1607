package com.example.fox_pipaw.gift;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fox_pipaw.Bean.GiftBigGiftBean;
import com.example.fox_pipaw.Bean.GiftGuessBean;
import com.example.fox_pipaw.Bean.GiftHeaderBean;
import com.example.fox_pipaw.CustomListView;
import com.example.fox_pipaw.Log.LogValue;
import com.example.fox_pipaw.R;
import com.example.fox_pipaw.http.HttpPost;
import com.example.fox_pipaw.http.HttpUrl;
import com.example.fox_pipaw.http.ImageAsyncLoader;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/10/23.
 */
public class FragmentGift extends Fragment {
    private TextView textView;
    private CustomListView mGuessListView;
    private List<GiftGuessBean.DataBean> dataGuess = new ArrayList<>();
    private GiftGuessAdapter giftGuessAdapter;
    private CustomListView mBigGiftListView;
    private List<GiftBigGiftBean.DataBean> dataGigGift = new ArrayList<>();
    private GiftBigAdapter giftBigAdapter;
    private List<GiftBigGiftBean.DataBean> dataGiftHot = new ArrayList<>();
    private CustomListView giftHotListView;
    private GiftBigAdapter giftHotAdapter;
    private List<GiftBigGiftBean.DataBean> dataGiftNew = new ArrayList<>();
    private CustomListView giftNewListView;
    private GiftBigAdapter giftNewAdapter;
    private ImageView mHotHeaderImage;
    private String imagePath;
    private ImageView mNewHeaderImage;
    private String imagePathTwo;
    private ViewPager headerViewPager;
    private List<GiftHeaderBean.DataBean>dataHeader = new ArrayList<>();
    private List<String> datas = new ArrayList<>();
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_main_gift, container, false);
        //猜你想要
        mGuessListView = (CustomListView)view.findViewById(R.id.gift_guesswant_lv);
        initDatas();
        giftGuessAdapter = new GiftGuessAdapter(dataGuess,getContext());
        mGuessListView.setAdapter(giftGuessAdapter);
        //豪华独家礼包
        mBigGiftListView = (CustomListView) view.findViewById(R.id.gift_big_gift_lv);
        giftBigAdapter = new GiftBigAdapter(dataGigGift,getContext());
        mBigGiftListView.setAdapter(giftBigAdapter);
        //热门推荐
        giftHotListView = (CustomListView) view.findViewById(R.id.gift_hot_lv);
        giftHotAdapter = new GiftBigAdapter(dataGiftHot,getContext());
        giftHotListView.setAdapter(giftHotAdapter);
        //最新礼包
        giftNewListView = (CustomListView) view.findViewById(R.id.gift_new_lv);
        giftNewAdapter = new GiftBigAdapter(dataGiftNew,getContext());
        giftNewListView.setAdapter(giftNewAdapter);
        //热门推荐头部图片
        mHotHeaderImage = (ImageView) view.findViewById(R.id.gift_hot_header_image);
        //最新礼包头部图片
        mNewHeaderImage = (ImageView) view.findViewById(R.id.gift_new_header_image);
        image1 = (ImageView) view.findViewById(R.id.gift_header_1_image);
        image2 = (ImageView) view.findViewById(R.id.gift_header_2_image);
        image3 = (ImageView) view.findViewById(R.id.gift_header_3_image);
        //添加头部
        return view;
    }

    private void initDatas() {
        RequestParams requestParamsHeader = new RequestParams(HttpUrl.giftHeaderUrl);
        requestParamsHeader.addBodyParameter(HttpPost.tokenName,HttpPost.tokenValue);
        requestParamsHeader.addBodyParameter(HttpPost.app_versionName,HttpPost.app_versionValue);
        x.http().post(requestParamsHeader, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogValue.log("android++","礼包头部————————————————————"+result);
                parseGiftHeader(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogValue.log("error",ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        RequestParams requestParamsImageTwo = new RequestParams(HttpUrl.giftImageUrl);
        requestParamsImageTwo.addBodyParameter(HttpPost.tokenName,HttpPost.tokenValue);
        requestParamsImageTwo.addBodyParameter(HttpPost.app_versionName,HttpPost.app_versionValue);
        requestParamsImageTwo.addBodyParameter(HttpPost.bannerName,HttpPost.bannerValue2);
        x.http().post(requestParamsImageTwo, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogValue.log("android++","最新礼包————————————————————"+result);
                parseNewHeader(result);
            }


            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        RequestParams requestParamsImage = new RequestParams(HttpUrl.giftImageUrl);
        requestParamsImage.addBodyParameter(HttpPost.tokenName,HttpPost.tokenValue);
        requestParamsImage.addBodyParameter(HttpPost.app_versionName,HttpPost.app_versionValue);
        requestParamsImage.addBodyParameter(HttpPost.bannerName,HttpPost.bannerValue1);
        x.http().post(requestParamsImage, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogValue.log("android++","最新礼包————————————————————"+result);
                parseHeader(result);
            }


            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        RequestParams requestParamsNew = new RequestParams(HttpUrl.giftNewBagUul);
        requestParamsNew.addBodyParameter(HttpPost.tokenName,HttpPost.tokenValue);
        requestParamsNew.addBodyParameter(HttpPost.app_versionName,HttpPost.app_versionValue);
        requestParamsNew.addBodyParameter(HttpPost.offsetName,HttpPost.offsetValue);
        requestParamsNew.addBodyParameter(HttpPost.limitName,HttpPost.limitValue);
        x.http().post(requestParamsNew, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogValue.log("android++","最新礼包————————————————————"+result);
                parseNew(result);
            }


            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        RequestParams requestParams = new RequestParams(HttpUrl.giftGuessUrl);
        requestParams.addBodyParameter(HttpPost.imName,HttpPost.imValue);
        requestParams.addBodyParameter(HttpPost.tokenName,HttpPost.tokenValue);
        requestParams.addBodyParameter(HttpPost.app_versionName,HttpPost.app_versionValue);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogValue.log("android++",result);
                parseGuess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        RequestParams requestParamsBig = new RequestParams(HttpUrl.giftBigGiftUrl);
        requestParamsBig.addBodyParameter(HttpPost.tokenName,HttpPost.tokenValue);
        requestParamsBig.addBodyParameter(HttpPost.app_versionName,HttpPost.app_versionValue);
        x.http().post(requestParamsBig, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogValue.log("android++",result);
                parseBigGift(result);
            }


            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        RequestParams requestParamsHot = new RequestParams(HttpUrl.giftHotUrl);
        requestParamsHot.addBodyParameter(HttpPost.tokenName,HttpPost.tokenValue);
        requestParamsHot.addBodyParameter(HttpPost.app_versionName,HttpPost.app_versionValue);
        x.http().post(requestParamsHot, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogValue.log("android++",result);
                parseHot(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
    private void parseGuess(String result) {
          Gson gson = new Gson();
        GiftGuessBean giftGuessBean = gson.fromJson(result, GiftGuessBean.class);
        List<GiftGuessBean.DataBean> data = giftGuessBean.getData();
        for (int i = 0; i < data.size(); i++) {
            GiftGuessBean.DataBean dataBean = data.get(i);
            String game_id = dataBean.getGame_id();
            String game_logo = dataBean.getGame_logo();
            String game_name = dataBean.getGame_name();
            int num = dataBean.getNum();
            String game_visits = dataBean.getGame_visits();
            LogValue.log("android++Gson++",game_logo);
            dataGuess.add(dataBean);
        }
        giftGuessAdapter.notifyDataSetChanged();
    }
    private void parseBigGift(String result) {
        Gson gson = new Gson();
        GiftBigGiftBean giftBigGiftBean = gson.fromJson(result, GiftBigGiftBean.class);
        List<GiftBigGiftBean.DataBean> data = giftBigGiftBean.getData();
        for (int i = 0; i < data.size(); i++) {
            GiftBigGiftBean.DataBean dataBean = data.get(i);
            String game_id = dataBean.getGame_id();
            String title = dataBean.getTitle();
            String description = dataBean.getDescription();
            double remain = dataBean.getRemain();
            int number = dataBean.getNumber();
            dataGigGift.add(dataBean);
        }
        giftBigAdapter.notifyDataSetChanged();
    }
    private void parseHot(String result) {
        Gson gson = new Gson();
        GiftBigGiftBean giftBigGiftBean = gson.fromJson(result, GiftBigGiftBean.class);
        List<GiftBigGiftBean.DataBean> data = giftBigGiftBean.getData();
        for (int i = 0; i < data.size(); i++) {
            GiftBigGiftBean.DataBean dataBean = data.get(i);
            String game_id = dataBean.getGame_id();
            String title = dataBean.getTitle();
            String description = dataBean.getDescription();
            double remain = dataBean.getRemain();
            int number = dataBean.getNumber();
            dataGiftHot.add(dataBean);
        }
        giftHotAdapter.notifyDataSetChanged();
    }
    private void parseNew(String result) {
        Gson gson = new Gson();
        GiftBigGiftBean giftBigGiftBean = gson.fromJson(result, GiftBigGiftBean.class);
        List<GiftBigGiftBean.DataBean> data = giftBigGiftBean.getData();
        for (int i = 0; i < data.size(); i++) {
            GiftBigGiftBean.DataBean dataBean = data.get(i);
            String game_id = dataBean.getGame_id();
            String title = dataBean.getTitle();
            String description = dataBean.getDescription();
            double remain = dataBean.getRemain();
            int number = dataBean.getNumber();
            dataGiftNew.add(dataBean);
        }
        giftNewAdapter.notifyDataSetChanged();
    }
    private void parseHeader(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject object = jsonObject.getJSONObject("data");
            String img = object.getString("img");
            imagePath = img;
            ImageAsyncLoader.load(imagePath,mHotHeaderImage).execute();
            LogValue.log("android_____+++++++++",imagePath);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void parseNewHeader(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject object = jsonObject.getJSONObject("data");
            String img = object.getString("img");
            imagePathTwo = img;
            ImageAsyncLoader.load(imagePathTwo,mNewHeaderImage).execute();
            LogValue.log("android_____+++++++++",imagePath);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseGiftHeader(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray data = jsonObject.getJSONArray("data");
            int length = data.length();
            for (int i = 0; i < length; i++) {
                JSONObject object = data.getJSONObject(i);
                String img = object.getString("img");
                datas.add(img);
            }
            for (int i = 0; i < 1; i++) {
                String path1 = datas.get(i);
                String path2 = datas.get(i+1);
                String path3 = datas.get(i+2);
                ImageAsyncLoader.load(path1,image1).execute();
                ImageAsyncLoader.load(path2,image2).execute();
                ImageAsyncLoader.load(path3,image3).execute();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
