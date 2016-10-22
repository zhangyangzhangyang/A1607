package com.example.gift_bag_spirit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GiftBagDetailsActivity extends AppCompatActivity {

    private static final String TAG = "android" ;
    private List<GiftBean> datas = new ArrayList<>();
    private ListView mListView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_bag_details);
        mListView = (ListView) findViewById(R.id.gift_bagdetails_list_view);
        Intent intent = getIntent();
        long beanId = intent.getLongExtra("beanId", 0);

        AsyncTaskTool.load("http://www.1688wan.com/majax.action?method=getGiftInfo").post("id="+beanId).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                paseJson(result);
            }
        });
        myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
    }

            private void paseJson(String result) {

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject jsonObj = jsonObject.getJSONObject("info");
                    //图片地址
                    String iconurl = jsonObj.getString("iconurl");
                    //有效期
                    String addtime = jsonObj.getString("addtime");
                    //剩余
                    Long exchanges = jsonObj.getLong("exchanges");
                    //礼包说名
                    String explains = jsonObj.getString("explains");
                    //兑奖方式
                    String descs = jsonObj.getString("descs");
                    //礼包名
                    String giftname = jsonObj.getString("giftname");
                    Log.i(TAG, "paseJson: +++++++++++++"+iconurl);
                    GiftBean bean = new GiftBean(exchanges,iconurl,explains,giftname,descs,addtime);
                    datas.add(bean);
                    myAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            Log.i(TAG, "getCount: "+ datas.size());
            return datas == null ? 0 :datas.size();
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
            View view = convertView;
            ViewHolder viewHolder = null;
            if(view == null){
                view = LayoutInflater.from(GiftBagDetailsActivity.this).inflate(R.layout.gift_bag_details_view,parent,false);
                viewHolder = new ViewHolder(view);
            }else{
               viewHolder = (ViewHolder) view.getTag();
            }
            GiftBean giftBean = datas.get(position);
            ImageAsyncLoader.load("http://www.1688wan.com" +giftBean.getPath(),viewHolder.mImageView).execute();
            viewHolder.mAddtime.setText("有效期"+giftBean.getTime());
            viewHolder.mNumber.setText("礼包剩余："+giftBean.getId());
            viewHolder.mState.setText(giftBean.getGname());
            viewHolder.mExpry.setText(giftBean.getResidue());
            return view;
        }
        class ViewHolder{
            public ImageView mImageView;
            public TextView mAddtime;
            public TextView mNumber;
            public TextView mState;
            public TextView mExpry;
            public ViewHolder(View view){
                view.setTag(this);
                mImageView = (ImageView) view.findViewById(R.id.gift_bag_details_img);
                 mAddtime = (TextView) view.findViewById(R.id.gift_bag_details_time_txt);
                mNumber= (TextView) view.findViewById(R.id.gift_bag_details_number_txt);
                 mState = (TextView) view.findViewById(R.id.gift_bag_state_details_txt);
                 mExpry = (TextView) view.findViewById(R.id.gift_bag_expiry_details_txt);
            }
        }
    }
}
