package com.cxk.TwoFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.chenxiaokang.commonlibrarytwo.AsyncTaskToolTwo;
import com.chenxiaokang.commonlibrarytwo.ImageAsyncLoader;
import com.cxk.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/10.
 */
public class TwoFragmentTwo extends Fragment{
    private final String PATH = "http://www.1688wan.com/majax.action?method=getJtkaifu";
    private ListView mListView;
    private Map<String,List<GiftBean2>> datas = new HashMap<>();
    private List<String> keys = new ArrayList<>();
    private ExpandableListView mExpandableListView;
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.two_fragment2,container,false);
        mExpandableListView = (ExpandableListView) view.findViewById(R.id.two_fragment2_list_view);
        loadDatas();
        myAdapter = new MyAdapter();
        mExpandableListView.setAdapter(myAdapter);

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

        return view;
    }

    private void loadDatas() {
        AsyncTaskToolTwo.load(PATH).execute(new AsyncTaskToolTwo.IMyCallback() {
            public static final String TAG = "android++++++";
            @Override
            public void success(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("info");
                    int length = jsonArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jsonObj = jsonArray.getJSONObject(i);
                        String addtime = jsonObj.getString("addtime");
                        List<GiftBean2> list = datas.get(addtime);
                        if(list == null){
                            list = new ArrayList<>();
                            keys.add(addtime);
                        }
                        String gname = jsonObj.getString("gname");
                        String iconurl = jsonObj.getString("iconurl");
                        String linkurl = jsonObj.getString("linkurl");
                        String operators = jsonObj.getString("operators");
                        String area = jsonObj.getString("area");
                        GiftBean2 bean = new GiftBean2(iconurl, gname, linkurl, operators,area);
                        list.add(bean);
                        for (int j = 0; j < keys.size(); j++) {
                            Log.i(TAG, "success: addtime"+keys.get(j));
                        }
                        Log.i(TAG, "success: gname"+gname);
                        datas.put(addtime,list);
                    }
                    myAdapter.notifyDataSetChanged();
                    for (int i = 0; i < keys.size(); i++) {
                        mExpandableListView.expandGroup(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    class MyAdapter extends BaseExpandableListAdapter{
        @Override
        public int getGroupCount() {
            return keys == null ? 0 : keys.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            String groupName = keys.get(groupPosition);
            List<GiftBean2> childList = datas.get(groupName);
            return childList == null ? 0 : childList.size();
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
            ViewHolder viewHolder = null;
            if(view == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.kaifu_one_time_view,parent,false);
                viewHolder = new ViewHolder(view);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.mTextView.setText(keys.get(groupPosition));
            return view;
        }
        class ViewHolder{
            TextView mTextView;
            public ViewHolder(View view){
                view.setTag(this);
                mTextView = (TextView) view.findViewById(R.id.kaifu_one_time_txt);
            }
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = convertView;
            ChildViewHolder childViewHolder = null;
            if(view == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.two_item,parent,false);
                childViewHolder = new ChildViewHolder(view);
            }else{
                childViewHolder  = (ChildViewHolder) view.getTag();
            }
            String key = keys.get(groupPosition);
            List<GiftBean2> beanList = datas.get(key);
            ImageAsyncLoader.load("http://www.1688wan.com"+beanList.get(childPosition).getPath(),childViewHolder.mImageView).execute();
            childViewHolder.mTitleTxt.setText(beanList.get(childPosition).getGname());
            childViewHolder.mTimeTxt.setText(beanList.get(childPosition).getLinkurl());
            childViewHolder.mAreaTxt.setText(beanList.get(childPosition).getArea());
            childViewHolder.mOprateTxt.setText(beanList.get(childPosition).getOperators());
            return view;
        }
        class ChildViewHolder {
            public ImageView mImageView;
            public TextView mTitleTxt;
            public TextView mTimeTxt;
            public TextView mOprateTxt;
            public TextView mAreaTxt;

            public ChildViewHolder(View view) {
                view.setTag(this);
                mImageView = (ImageView) view.findViewById(R.id.two_item_image_view);
                mTitleTxt = (TextView) view.findViewById(R.id.two_item_text_gname);
                mTimeTxt = (TextView) view.findViewById(R.id.two_item_text_linkurl);
                mOprateTxt = (TextView) view.findViewById(R.id.two_item_text_operators);
                mAreaTxt=(TextView) view.findViewById(R.id.two_item_text_area);
            }
        }
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
