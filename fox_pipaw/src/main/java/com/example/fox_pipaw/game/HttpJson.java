package com.example.fox_pipaw.game;

import com.example.fox_pipaw.Bean.GameTodayBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/10/25.
 */
public class HttpJson {
    public List<GameTodayBean> parseJson(String result,TodayAdapter todayAdapter) {
        List<GameTodayBean>datasToday = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(result);
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String logo = jsonObject.getString("logo");
                String game_name = jsonObject.getString("game_name");
                String type_name = jsonObject.getString("type_name");
                String game_visits = jsonObject.getString("game_visits");
                String desc1 = jsonObject.getString("desc1");
                String game_id = jsonObject.getString("game_id");
                JSONObject download_data = jsonObject.getJSONObject("download_data");
                String size = download_data.getString("size");
                String real_down_url = download_data.getString("real_down_url");
//                new GameTodayBean(logo,game_name,type_name,)
                GameTodayBean gameTodayBean = new GameTodayBean(logo, game_name, type_name, size, game_visits, desc1, real_down_url, game_id);
                datasToday.add(gameTodayBean);
            }

//            todayAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datasToday;
    }
}
