package com.example.fox_pipaw.game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.fox_pipaw.Bean.GameHeaderBean;
import com.example.fox_pipaw.Bean.GameTodayBean;
import com.example.fox_pipaw.CustomGridView;
import com.example.fox_pipaw.CustomListView;
import com.example.fox_pipaw.R;
import com.example.fox_pipaw.game.listdetails.TodayDetailsActivity;
import com.example.fox_pipaw.http.AsyncTaskTool;
import com.example.fox_pipaw.http.HttpUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/10/23.
 */
public class FragmentGame extends Fragment {

    private static final String TAG = "android++";
    private List<GameTodayBean> datasToday = new ArrayList<>();
    private List<GameTodayBean> datasToday1 = new ArrayList<>();
    private List<GameTodayBean> datasToday2 = new ArrayList<>();
    private List<GameTodayBean> datasToday3 = new ArrayList<>();
    private List<GameHeaderBean> datasHeader = new ArrayList<>();
    private TodayAdapter todayAdapter;
    private TodayAdapter todayAdapter1;
    private TodayAdapter todayAdapter2;
    private CustomListView mTodayListView;
    private CustomListView mHotListView;
    private CustomListView mWelfareListView;
    private CustomGridView mSelectGridView;
    private SelectAdapter selectAdapter;
    private CustomListView mSortListView;
    private SortAdapter sortAdapter;
    private GameHeaderAdapter gameHeaderAdapter;
    private ViewPager mViewPager;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int index = msg.what;
            mViewPager.setCurrentItem(index % 4);
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_main_game, container, false);
        //今日首发的listview
        mTodayListView = (CustomListView) view.findViewById(R.id.game_today_lv);
        //热门的listview
        mHotListView = (CustomListView)view.findViewById(R.id.game_hot_lv);
        //福利的listview
        mWelfareListView =(CustomListView) view.findViewById(R.id.game_welfare_lv);
        //更多选择的listview
        mSelectGridView = (CustomGridView)view.findViewById(R.id.game_select_gv);
        //分类游戏和游戏排行的listview
        mSortListView = (CustomListView)view.findViewById(R.id.game_sort_list);
        //游戏头部view
        View gameHeaderView = inflater.inflate(R.layout.game_header_viewpager, mSortListView, false);
        //游戏头部的viewpager
        mViewPager = (ViewPager) gameHeaderView.findViewById(R.id.game_header_view_pager);
        //今日首发适配
        todayAdapter = new TodayAdapter(datasToday,getContext());
        mTodayListView.setAdapter(todayAdapter);
        todayAdapter1 = new TodayAdapter(datasToday1,getContext());
        mHotListView.setAdapter(todayAdapter1);
        todayAdapter2 = new TodayAdapter(datasToday2,getContext());
        mWelfareListView.setAdapter(todayAdapter2);
        //分类游戏的适配
        sortAdapter = new SortAdapter();
        //添加头部
        mSortListView.addHeaderView(gameHeaderView);
        gameHeaderAdapter = new GameHeaderAdapter(datasHeader,getContext());
        mSortListView.setAdapter(sortAdapter);
        mViewPager.setAdapter(gameHeaderAdapter);

        selectAdapter = new SelectAdapter(datasToday3,getContext());
        mSelectGridView.setAdapter(selectAdapter);
        HttpLoadData();

        //今日首发跳转
        mTodayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent(getContext(), TodayDetailsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }



    private void HttpLoadData(){
        AsyncTaskTool.load(HttpUrl.gameTodayUrl).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {

                parse(result);
            }
        });
        AsyncTaskTool.load(HttpUrl.gameHotUrl).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                parse1(result);
            }
        });
        AsyncTaskTool.load(HttpUrl.gameWelfareUrl).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                parse2(result);

            }
        });
        AsyncTaskTool.load(HttpUrl.gameSelectUrl).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
//                Log.i(TAG, "success: "+result);
                parse3(result);
            }
        });
        AsyncTaskTool.load(HttpUrl.gameHeaderUrl).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                parseHeader(result);
            }


        });

    }
    private void parseHeader(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String game_id = jsonObject.getString("game_id");
                String img = jsonObject.getString("img");
                GameHeaderBean bean = new GameHeaderBean(game_id, img);
                datasHeader.add(bean);
            }
            gameHeaderAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                while (true){
                    try {
                        Thread.sleep(4000);
                        mHandler.sendEmptyMessage(index);
                        index++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void parse(String result) {
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
            todayAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void parse1(String result) {
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
                datasToday1.add(gameTodayBean);
            }
            todayAdapter1.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void parse2(String result){
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
                datasToday2.add(gameTodayBean);
            }
            todayAdapter2.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void parse3(String result) {
        try {
//            Log.i(TAG, "parse3: "+result);
            JSONArray jsonArray = new JSONArray(result);
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String logo = jsonObject.getString("logo");
                String game_name = jsonObject.getString("game_name");
                String type_name = jsonObject.getString("type_name");
                String game_visits = jsonObject.getString("game_visits");
//                String desc1 = jsonObject.getString("desc1");
                String game_id = jsonObject.getString("game_id");
//                JSONObject download_data = jsonObject.getJSONObject("download_data");
//                String real_down_url;
//                if (download_data == null ){
//                    real_down_url = null;
//                }else{
//                real_down_url = download_data.getString("real_down_url");
//                }
//                Log.i(TAG, "parse3: ++"+gameTodayBean.toString());
//                String size = download_data.getString("size");
                GameTodayBean gameTodayBean = new GameTodayBean(logo, game_name, type_name, null, game_visits, null, "123", game_id);
                datasToday3.add(gameTodayBean);
            }
//            Log.i(TAG, "parse3:++++++++++ "+ datasToday3.toString());
            selectAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    class SortAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView =LayoutInflater.from(getContext()).inflate(R.layout.game_sort_details,parent,false);
            Button sortBtn1 = (Button) convertView.findViewById(R.id.game_sort_list_1_btn);
            sortBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(),SortGameActivity.class);
                    startActivity(intent);
                }
            });
            Button sortBtn2 = (Button) convertView.findViewById(R.id.game_sort_list_2_btn);
            sortBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(),NewAndHotActivity.class);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }

}