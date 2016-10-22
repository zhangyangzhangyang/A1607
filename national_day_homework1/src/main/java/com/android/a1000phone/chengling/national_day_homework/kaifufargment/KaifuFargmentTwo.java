package com.android.a1000phone.chengling.national_day_homework.kaifufargment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;

import com.android.a1000phone.chengling.national_day_homework.R;
import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengling on 2016/10/10.
 */
public class KaifuFargmentTwo extends Fragment{
    private final String PATH =  "http://www.1688wan.com/majax.action?method=getJtkaifu";
    private ListView mListView;
    private Map<String,List<String>> datas = new HashMap<>();
    private List<String> keys = new ArrayList<>();
    private List<Kaifu_one_Bean> datas2 = new ArrayList<>();
    private KaifuAdapter2 kaifuAdapter2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kaifu_main_view_item,container,false);
        mListView = (ListView) view.findViewById(R.id.kaifu_one_main_view);
        initData();
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    private void initData() {
        AsyncTaskTool.load(PATH).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("info");
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String addtime = jsonObject1.getString("addtime");
                        keys.add(addtime);
                        ArrayList<Kaifu_one_Bean> groupValues = new ArrayList<Kaifu_one_Bean>();
                        String id = jsonObject1.getString("id");
                        String iconurl = jsonObject1.getString("iconurl");
                        String gname = jsonObject1.getString("gname");
                        String linkurl = jsonObject1.getString("linkurl");
                        String area = jsonObject1.getString("area");
                        String operators = jsonObject1.getString("operators");
                        Kaifu_one_Bean bean = new Kaifu_one_Bean(id,operators,area,linkurl,gname,iconurl,addtime);
                        groupValues.add(bean);
                        datas2.add(bean);

                    }
                    kaifuAdapter2.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    class KaifuAdapter2 extends BaseExpandableListAdapter{

        @Override
        public int getGroupCount() {
            return keys == null ? 0 :keys.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            String groupName = keys.get(groupPosition);
            List<String> childrenList = datas.get(groupName);
            return childrenList == null ? 0 :childrenList.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
//                view = LayoutInflater.from(getContext()).inflate()

            }
            return null;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
