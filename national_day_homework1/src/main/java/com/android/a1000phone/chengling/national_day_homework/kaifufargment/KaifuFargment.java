package com.android.a1000phone.chengling.national_day_homework.kaifufargment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.a1000phone.chengling.national_day_homework.R;
import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengling on 2016/10/7.
 */
public class KaifuFargment extends Fragment{
    private final String PATH = "http://www.1688wan.com/majax.action?method=getJtkaifu";
    private ListView mListView;
    private Map<String,List<Kaifu_one_Bean>> datas = new HashMap<>();
    private List<String> keys = new ArrayList<>();
    private List<Kaifu_one_Bean> totalDatas = new ArrayList<>();
    private KaifuAdapter kaifuAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kaifu_main_view_item,container,false);
        mListView = (ListView)view.findViewById(R.id.kaifu_one_main_view);
        loadDatas();
        kaifuAdapter = new KaifuAdapter();
        mListView.setAdapter(kaifuAdapter);
        return view;
    }

    private void loadDatas() {
        AsyncTaskTool.load(PATH).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("info");
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject jsonObj = jsonArray.getJSONObject(i);
                        String addtime = jsonObj.getString("addtime");
                        List<Kaifu_one_Bean> list = datas.get(addtime);
                        if (list == null){
                            list = new ArrayList<Kaifu_one_Bean>();
                            datas.put(addtime,list);
                            keys.add(addtime);
                        }
                        String id = jsonObj.getString("id");
                        String iconurl = jsonObj.getString("iconurl");
                        String gname = jsonObj.getString("gname");
                        String linkurl = jsonObj.getString("linkurl");
                        String area = jsonObj.getString("area");
                        String operators = jsonObj.getString("operators");
                        Kaifu_one_Bean bean = new Kaifu_one_Bean(id,operators,area,linkurl,gname,iconurl,addtime);
                        list.add(bean);
                        totalDatas.add(bean);
                    }
                    kaifuAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    class KaifuAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return totalDatas == null ? 0 : totalDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            Kaifu_one_Bean temp = totalDatas.get(position);
            if (datas.containsKey(temp)) {
                return 1;
            }
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            int type = getItemViewType(position);
            ViewHolder1 viewHolder1 = null;
            ViewHoder2 viewHolder2 = null;
            if (view == null) {
                switch (type) {
                    case 1:
                        view = LayoutInflater.from(getContext()).inflate(R.layout.kaifu_one_time, parent, false);
                        viewHolder1 = new ViewHolder1(view);
                        break;
                    default:
                        view = LayoutInflater.from(getContext()).inflate(R.layout.kaifu_one, parent, false);
                        viewHolder2 = new ViewHoder2(view);
                        break;
                }
            } else {
                switch (type) {
                    case 1:
                        viewHolder1 = (ViewHolder1) view.getTag();
                        break;
                    default:
                        viewHolder2 = (ViewHoder2) view.getTag();
                        break;
                }
            }
            switch (type) {
                case 1:
                    viewHolder1.mGroupText.setText(totalDatas.get(position).getAddtime());
                    break;
                default:
                    ImageAsyncLoader.load("http://www.1688wan.com" + totalDatas.get(position).getPath(), viewHolder2.mImageView).execute();
                    viewHolder2.mTitle.setText(totalDatas.get(position).getTitle());
                    viewHolder2.mTime.setText(totalDatas.get(position).getTime());
                    viewHolder2.mArea.setText(totalDatas.get(position).getArea());
                    viewHolder2.mOprate.setText(totalDatas.get(position).getOperate());
                    break;
            }
            return view;
        }

        class ViewHolder1 {
            public TextView mGroupText;

            public ViewHolder1(View view) {
                view.setTag(this);
                mGroupText = (TextView) view.findViewById(R.id.kaifu_one_time_txt);
            }
        }

        class ViewHoder2 {
            public ImageView mImageView;
            public TextView mTitle;
            public TextView mTime;
            public TextView mOprate;
            public TextView mArea;
            public ViewHoder2(View view) {
                view.setTag(this);
                mArea = (TextView)view.findViewById(R.id.kaifu_one_list_number);
                mImageView = (ImageView) view.findViewById(R.id.kaifu_one_list_img);
                mTitle = (TextView) view.findViewById(R.id.kaifu_one_list_title_txt);
                mTime = (TextView) view.findViewById(R.id.kaifu_one_list_time_txt);
                mOprate = (TextView) view.findViewById(R.id.kaifu_one_list_operate_txt);
            }

        }
    }
}