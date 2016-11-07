package com.example.fox_pipaw.game.model;

import com.example.fox_pipaw.Bean.NewAndHotBean;
import com.example.fox_pipaw.game.preseter.IRequestCallback;
import com.example.fox_pipaw.http.AsyncTaskTool;
import com.example.fox_pipaw.http.HttpUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/10/27.
 */
public class GameNewModel implements IGameModel {
    @Override
    public void queryGiftList(final IRequestCallback callback) {
        AsyncTaskTool.load(HttpUrl.gameNewAndNewUrl).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                List<NewAndHotBean> datas = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    int length = jsonArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String descript = jsonObject.getString("descript");
                        String fahao_count = jsonObject.getString("fahao_count");
                        String game_id = jsonObject.getString("game_id");
                        String game_logo = jsonObject.getString("game_logo");
                        String game_name = jsonObject.getString("game_name");
                        String game_visits = jsonObject.getString("game_visits");
                        String size = jsonObject.getString("size");
                        String game_type = jsonObject.getString("game_type");
                        if (game_type.equals("")){
                            game_type = "  ";
                        }
                        NewAndHotBean newAndHotBean = new NewAndHotBean(descript, fahao_count, game_id, game_logo, game_name, game_visits, size,game_type);
                        datas.add(newAndHotBean);
                    }
                    callback.callback(datas);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
