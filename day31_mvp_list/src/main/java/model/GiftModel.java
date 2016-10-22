package model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import http.HttpUrl;
import http.HttpUtils;
import preserter.IRequestCallback;

/**
 * Created by 张样 on 2016/10/22.
 */
public class GiftModel implements IGiftModel{

    @Override
    public void queryGiftList(final IRequestCallback callback) {
        HttpUtils.request(HttpUrl.PATH, false, null, new HttpUtils.ICallBack() {
            @Override
            public void success(String result) {
                List<String> datas = new ArrayList<String>();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("info");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String gname = jsonObject1.getString("gname");
                        Log.i("android", "success: " + gname);
                        datas.add(gname);
                    }
                    callback.callback(datas);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
