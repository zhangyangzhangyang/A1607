package com.example.com.example.kaifuFragment;

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

import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;
import com.example.gift_bag_spirit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/10/6.
 */
public class KaifuTwoFragment extends Fragment {
    private ListView mListView;
    private Context mContext;
    private final String PATH ="http://www.1688wan.com/majax.action?method=getWebfutureTest";
    List<KaifuTwoBean> datas = new ArrayList<>();
    private KaifuTwoAdapter kaifuTwoAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kaifu_two_view,container,false);
        mListView = (ListView) view.findViewById(R.id.kaifu_two_main_view);
        kaifuTwoAdapter = new KaifuTwoAdapter();
        mListView.setAdapter(kaifuTwoAdapter);
        AsyncTaskTool.load(PATH).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                paeseJson(result);
            }


        });
        return view;
    }
    private void paeseJson(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("info");
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String id = jsonObj.getString("id");
                String iconurl = jsonObj.getString("iconurl");
                String gname = jsonObj.getString("gname");
                String operators = jsonObj.getString("operators");
                String addtime = jsonObj.getString("addtime");
                KaifuTwoBean bean = new KaifuTwoBean(id,addtime,operators,gname,iconurl);
                datas.add(bean);
            }
            kaifuTwoAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    class KaifuTwoAdapter extends BaseAdapter{

        private static final String PREFIX = "http://www.1688wan.com";
        private LayoutInflater mLayoutInflater;

        public KaifuTwoAdapter() {
            super();
            this.mLayoutInflater = LayoutInflater.from(mContext);
        }
        @Override
        public int getCount() {
            return datas == null ? 0 :datas.size();
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
            if (view == null){
                view = mLayoutInflater.inflate(R.layout.kaifu_two_list_view,parent,false);
                viewHolder = new ViewHolder(view);
            }else{
                viewHolder = (ViewHolder) view.getTag();

            }
            KaifuTwoBean bean = datas.get(position);
            ImageAsyncLoader.load(PREFIX+bean.getPath(),viewHolder.mImageView).execute();
            viewHolder.mTitleTxt.setText(bean.getTitle());
            viewHolder.mTimeTxt.setText(bean.getTime());
            viewHolder.mOprateTxt.setText(bean.getOprate());
            return view;
        }
        class  ViewHolder{
            public ImageView mImageView;
            public TextView mTitleTxt;
            public TextView mTimeTxt;
            public TextView mOprateTxt;
            public ViewHolder(View view){
                view.setTag(this);
                mImageView = (ImageView) view.findViewById(R.id.kaifu_two_list_img);
                mTitleTxt = (TextView) view.findViewById(R.id.kaifu_two_list_title_txt);
                mTimeTxt = (TextView) view.findViewById(R.id.kaifu_two_list_time_txt);
                mOprateTxt = (TextView) view.findViewById(R.id.kaifu_two_list_operate_txt);
            }
        }
    }
}
