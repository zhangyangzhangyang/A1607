package com.android.a1000phone.chengling.national_day_homework.kaifufargment;

import android.content.Context;
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
import java.util.List;

/**
 * Created by chengling on 2016/10/7.
 */
public class KaiceFargment extends Fragment{
    private ListView mListView;
    private Context mContext;
    private final String PATH ="http://www.1688wan.com/majax.action?method=getWebfutureTest";
    List<Kaice_one_Bean> datas = new ArrayList<>();
    private KaiceAdapter kaiceAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kaifu_two_view,container,false);
        mListView = (ListView)view.findViewById(R.id.kaifu_two_main_view);
        kaiceAdapter = new KaiceAdapter();
        mListView.setAdapter(kaiceAdapter);
        AsyncTaskTool.load(PATH).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                perseJson(result);
            }
        });
        return view;
    }

    private void perseJson(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("info");
            int len = jsonArray.length();
            for (int i = 0; i <len ; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String id = jsonObj.getString("id") == null ? "" : jsonObj.getString("id");
                String iconurl = jsonObj.getString("iconurl") == null ? "" : jsonObj.getString("iconurl");
                String gname = jsonObj.getString("gname") ==  null ? "" : jsonObj.getString("gname");
                String operators = jsonObj.getString("operators") == null ? "" : jsonObj.getString("operators");
                String addtime = jsonObj.getString("addtime") == null ? "" : jsonObj.getString("addtime");
                Kaice_one_Bean bean = new Kaice_one_Bean(id,iconurl,gname,operators,addtime);
                datas.add(bean);
            }
            kaiceAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    class KaiceAdapter extends BaseAdapter {
        private static final String PREFIX = "http://www.1688wan.com";
        private LayoutInflater mLayoutInflater;

        public KaiceAdapter() {
            super();
            this.mLayoutInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder viewHolder = null;
            if (view == null) {
                view = mLayoutInflater.inflate(R.layout.kaifu_two_list_view, parent, false);
                viewHolder = new ViewHolder(view);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Kaice_one_Bean bean = datas.get(position);
            ImageAsyncLoader.load(PREFIX + bean.getPath(), viewHolder.mImageView).execute();
            viewHolder.mTitleTxt.setText(bean.getTitle());
            viewHolder.mTimeTxt.setText(bean.getTime());
            viewHolder.mOprateTxt.setText(bean.getOprate());
            return view;
        }

        class ViewHolder {
            public ImageView mImageView;
            public TextView mTitleTxt;
            public TextView mTimeTxt;
            public TextView mOprateTxt;

            public ViewHolder(View view) {
                view.setTag(this);
                mImageView = (ImageView) view.findViewById(R.id.kaifu_two_list_img);
                mTitleTxt = (TextView) view.findViewById(R.id.kaifu_two_list_title_txt);
                mTimeTxt = (TextView) view.findViewById(R.id.kaifu_two_list_time_txt);
                mOprateTxt = (TextView) view.findViewById(R.id.kaifu_two_list_operate_txt);
            }
        }

    }
}
