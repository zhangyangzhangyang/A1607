package com.example.com.example.kaifuFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;
import com.example.gift_bag_spirit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张样 on 2016/10/6.
 */
public class KaifuOneTFragment extends Fragment {

    private final String PATH = "http://www.1688wan.com/majax.action?method=getJtkaifu";
    private ListView mListView;
    private Map<String,List<KaifuOneBean>> datas = new HashMap<>();
    private List<String> keys = new ArrayList<>();
    private ExpandableListView mExpandableListView;
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kaifu_one_view,container,false);
        mExpandableListView = (ExpandableListView) view.findViewById(R.id.kaifu_one_expandable_view);
        loadDatas();
        myAdapter = new MyAdapter();
        mExpandableListView.setAdapter(myAdapter);

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
//        mExpandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public static final String TAG = "android++";
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String str = keys.get(position);
//                List<KaifuOneBean> kaifuOneBeen = datas.get(str);
//                String path = kaifuOneBeen.get(position).getPath();
//                Log.i(TAG, "onItemClick: "+ path);
//            }
//        });

        return view;
    }

    private void loadDatas() {
        AsyncTaskTool.load(PATH).execute(new AsyncTaskTool.IMyCallback() {
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
                        List<KaifuOneBean> list = datas.get(addtime);
                        if(list == null){
                            list = new ArrayList<>();
                            keys.add(addtime);
                        }
                        String id = jsonObj.getString("gid");
                        String iconurl = jsonObj.getString("iconurl");
                        String gname = jsonObj.getString("gname");
                        String linkurl = jsonObj.getString("linkurl");
                        String area = jsonObj.getString("area");
                        String operators = jsonObj.getString("operators");
                        KaifuOneBean bean = new KaifuOneBean(id,operators,area,linkurl,gname,iconurl,addtime);
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
            List<KaifuOneBean> childList = datas.get(groupName);
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
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = convertView;
            ChildViewHolder childViewHolder = null;
            if(view == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.kaifu_one_list_view,parent,false);
                childViewHolder = new ChildViewHolder(view);
            }else{
                childViewHolder  = (ChildViewHolder) view.getTag();
            }
            String key = keys.get(groupPosition);
            final List<KaifuOneBean> beanList = datas.get(key);
            ImageAsyncLoader.load("http://www.1688wan.com"+beanList.get(childPosition).getPath(),childViewHolder.mImageView).execute();
            childViewHolder.mTitleTxt.setText(beanList.get(childPosition).getTitle());
            childViewHolder.mTimeTxt.setText(beanList.get(childPosition).getTime());
            childViewHolder.mOprateTxt.setText(beanList.get(childPosition).getOperate());
            childViewHolder.mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = beanList.get(childPosition).getId();
                    Intent intent = new Intent(getContext(),KaifuOneDetails.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
//                    Toast.makeText(getContext(),id,Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }
        class ChildViewHolder {
            public ImageView mImageView;
            public TextView mTitleTxt;
            public TextView mTimeTxt;
            public TextView mOprateTxt;
            public Button mButton;

            public ChildViewHolder(View view) {
                view.setTag(this);
                mImageView = (ImageView) view.findViewById(R.id.kaifu_one_list_img);
                mTitleTxt = (TextView) view.findViewById(R.id.kaifu_one_list_title_txt);
                mTimeTxt = (TextView) view.findViewById(R.id.kaifu_one_list_time_txt);
                mOprateTxt = (TextView) view.findViewById(R.id.kaifu_one_list_operate_txt);
                mButton = (Button) view.findViewById(R.id.kaifu_one_list_btn);
//                mButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getContext(),"zhangyangzhangyang",Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }

}
