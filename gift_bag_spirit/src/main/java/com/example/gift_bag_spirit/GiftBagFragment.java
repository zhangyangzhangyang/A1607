package com.example.gift_bag_spirit;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/9/27.
 */
public class GiftBagFragment extends Fragment {
    private ListView mListView;
    private final String PATH ="http://www.1688wan.com/majax.action?method=getGiftList";
    private List<GiftBean> datas = new ArrayList<>();
    private List<String> imagePaths = new ArrayList<>();
    private MyAdapter myAdapter ;
    private Context mContext;
    private ViewPager mViewPager;
    private BannerAdapter bannerAdapeter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gift_bag_main_view, container,false);
        mListView = (ListView)view.findViewById(R.id.gift_bag_list_view);
        mViewPager = (ViewPager) view.findViewById(R.id.libao_heander_viewpager);
        AsyncTaskTool.load(PATH).post("pageno=1").execute(new AsyncTaskTool.IMyCallback() {
                    @Override
                    public void success(String result) {
                        paeseJsonImage( result);
                        parseJsonItem(result);
                    }
                }) ;
        bannerAdapeter = new BannerAdapter();
        mViewPager.setAdapter(bannerAdapeter);
        myAdapter = new MyAdapter(mContext);
        mListView.setAdapter(myAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"ListView______show",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    private void paeseJsonImage(String result){
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("ad");
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                JSONObject adObject = jsonArray.getJSONObject(i);
                String id = adObject.getString("id");
                String iconurl = adObject.getString("iconurl");
                imagePaths.add(id);
                imagePaths.add(iconurl);
            }
            bannerAdapeter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    class  BannerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imagePaths == null ? 0 : imagePaths.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            container.addView(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String path = imagePaths.get(position);
            String imgPath =  "http://www.1688wan.com" + path;
            ImageAsyncLoader.load(imgPath,imageView).execute();
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

   private void parseJsonItem(String result){
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            int listLen = jsonArray.length();
            for (int i = 0; i < listLen; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                Long id = jsonObj.getLong("id");
                String iconurl = jsonObj.getString("iconurl");
                String gname = jsonObj.getString("gname");
                String giftname = jsonObj.getString("giftname");
                //剩余数量
                String number = jsonObj.getString("number");
                String addtime = jsonObj.getString("addtime");
                GiftBean giftBean = new GiftBean(id,iconurl,gname,giftname,"剩余："+number,"时间："+addtime);
                datas.add(giftBean);
            }
            myAdapter.notifyDataSetChanged();
          } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    class MyAdapter extends BaseAdapter {
        private static final String PREFIX = "http://www.1688wan.com";
        private LayoutInflater mLayoutInflater;

        public MyAdapter(Context context) {
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
                if (view == null){
                    view = mLayoutInflater.inflate(R.layout.gift_bag_view,parent,false);
                    viewHolder = new ViewHolder(view);
                }else{
                    viewHolder = (ViewHolder) view.getTag();
                }
        GiftBean bean = datas.get(position);
        ImageAsyncLoader.load(PREFIX+bean.getPath(),viewHolder.mImageView).execute();
        viewHolder.mTitleTxt.setText(bean.getGname());
        viewHolder.mGiftNameTxt.setText(bean.getGiftName());
        viewHolder.mTimeTxt.setText(bean.getResidue()+"  "+bean.getTime());
        return view;
    }
    class ViewHolder{
        public ImageView mImageView;
        public TextView mTitleTxt;
        public TextView mGiftNameTxt;
        public TextView mTimeTxt;
        public Button mButton;
        public ViewHolder(View view){
            view.setTag(this);
             mImageView = (ImageView) view.findViewById(R.id.gift_bag_img);
            mTitleTxt = (TextView) view.findViewById(R.id.gift_bag_title_txt);
            mGiftNameTxt = (TextView) view.findViewById(R.id.gift_bag_name_txt);
            mTimeTxt = (TextView) view.findViewById(R.id.gift_bag_time_txt);
            mButton = (Button) view.findViewById(R.id.gift_bag_btn);
        }
    }
  }
    public void listClick(View view){

    }
}
