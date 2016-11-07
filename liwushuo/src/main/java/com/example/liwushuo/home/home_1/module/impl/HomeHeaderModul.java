package com.example.liwushuo.home.home_1.module.impl;

import com.example.liwushuo.home.home_1.module.HomeHeaderBean;
import com.example.liwushuo.home.home_1.module.IHomeHeaderModul;
import com.example.liwushuo.http.HttpUrl;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by 张样 on 2016/11/6.
 */
public class HomeHeaderModul implements IHomeHeaderModul{
    @Override
    public void queryHeader(final com.example.liwushuo.home.home_1.presenter.callback.Callback callback) {
        RequestParams requestParams = new RequestParams(HttpUrl.HOME_1_HEADER_URL);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HomeHeaderBean homeHeaderBean = gson.fromJson(result, HomeHeaderBean.class);
                if (callback != null){
                    callback.success(homeHeaderBean);
                }
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
}
