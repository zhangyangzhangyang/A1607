package com.cxk.OneFragment;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.chenxiaokang.commonlibrarytwo.AsyncTaskToolTwo;
import com.chenxiaokang.commonlibrarytwo.ImageAsyncLoader;
import com.cxk.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class GiftMessageActivity extends AppCompatActivity implements AsyncTaskToolTwo.IMyCallback{
    private List<GiftBeanMessage> datas = new ArrayList<GiftBeanMessage>();
    private MyAdapter mAdapter;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_message);
        mAdapter = new MyAdapter();
        Intent intent=getIntent();
        String gid=intent.getStringExtra("gid");
        Log.i(TAG, "onCreate: "+gid);
        AsyncTaskToolTwo.load("http://www.1688wan.com/majax.action?method=getGiftInfo&id="+gid)
                .execute(this);
    }
    public void success(String result){
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray listArray = jsonObject.getJSONArray("info");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject jsonObj = listArray.getJSONObject(i);
                String path = jsonObj.getString("iconurl");
                String time= jsonObj.getString("overtime");
                Log.i(TAG, "success: "+time);
                String left= jsonObj.getString("exchanges");
                String jieshi=jsonObj.getString("explains");
                String gettype = jsonObj.getString("descs");
                GiftBeanMessage giftBean=new GiftBeanMessage(path,time,left,jieshi,gettype);
                datas.add(giftBean);
            }
            mAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class MyAdapter extends BaseAdapter {
        private static final String PREFIX = "http://www.1688wan.com";

        private LayoutInflater mLayoutInflater;

        public MyAdapter() {
            super();
            mLayoutInflater = LayoutInflater.from(GiftMessageActivity.this);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return datas == null ? 0 : datas.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder mViewHolder = null;
            if (view == null) {
                view = mLayoutInflater.inflate(R.layout.activity_item_gift_message, parent, false);
                mViewHolder = new ViewHolder(view);
            } else {
                mViewHolder = (ViewHolder) view.getTag();
            }
            GiftBeanMessage giftBean = datas.get(position);
            mViewHolder.mTime.setText("剩余：" + giftBean.getTime());
            mViewHolder.mLeft.setText("时间:" + giftBean.getLeft());
            mViewHolder.mJieShi.setText("礼包说明:" + giftBean.getJieshi());
            mViewHolder.mGetType.setText("兑换方式:" + giftBean.getGettype());
            ImageAsyncLoader.load(PREFIX + giftBean.getPath(), mViewHolder.mImageView2).execute();
            return view;
        }
        class ViewHolder {
            public ImageView mImageView2;
            public TextView mTime;
            public TextView mLeft;
            public TextView mJieShi;
            public TextView mGetType;
            public ViewHolder(View view) {
                view.setTag(this);
                mImageView2 = (ImageView) view.findViewById(R.id.activity_gift_image_view2);
                mTime = (TextView) view.findViewById(R.id.activity_gift_text_time);
                mLeft = (TextView) view.findViewById(R.id.activity_gift_text_left);
                mJieShi = (TextView) view.findViewById(R.id.activity_gift_text_jieshi);
                mGetType = (TextView) view.findViewById(R.id.activity_gift_text_gettype);
            }
        }
    }
}