package com.example.day22_expandable_listview;

import android.app.Activity;
import android.os.BaseBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView mExpandableListView;
    private Map<String,List<String>> datas = new HashMap<>();
    private List<String> keys = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mExpandableListView = (ExpandableListView) findViewById(R.id.main_expandable_listview);
        initDatas();
        myAdapter = new MyAdapter();
        mExpandableListView.setAdapter(myAdapter);
        for (int i = 0; i < keys.size(); i++) {
            mExpandableListView.expandGroup(i);
        }
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    private void initDatas() {
        for (int i = 0; i < 20; i++) {
            String groupName = "GROUP" + i;
            keys.add(groupName);
            ArrayList<String> groupValues = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                groupValues.add("ITEM" + j);
            }
            datas.put(groupName,groupValues);
        }
    }
    class MyAdapter extends BaseExpandableListAdapter{
        @Override
        public int getGroupCount() {
            return keys == null ? 0 : keys.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            String groupName = keys.get(groupPosition);
            List<String> childList = datas.get(groupName);
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
               view = LayoutInflater.from(MainActivity.this).inflate(R.layout.group_item_view,parent,false);
                viewHolder = new ViewHolder(view);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.mTextView.setText(keys.get(groupPosition));
            return view;
        }
        class ViewHolder{
            TextView mTextView;

            public ViewHolder(View view) {
                view.setTag(this);
                mTextView = (TextView) view.findViewById(R.id.group_item_view_txt);
            }
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = convertView;
            ChildViewHolder childViewHolder = null;
            if(view == null){
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.child_item_view,parent,false);
                childViewHolder = new ChildViewHolder(view);
            }else{
                childViewHolder = (ChildViewHolder) view.getTag();
            }
            String key = keys.get(groupPosition);
            List<String> childList = datas.get(key);
            childViewHolder.mTextView.setText(childList.get(childPosition));
            return view;
        }
        class ChildViewHolder{
            TextView mTextView;

            public ChildViewHolder(View view) {
                view.setTag(this);
                mTextView = (TextView) view.findViewById(R.id.child_view_txt);
            }
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
