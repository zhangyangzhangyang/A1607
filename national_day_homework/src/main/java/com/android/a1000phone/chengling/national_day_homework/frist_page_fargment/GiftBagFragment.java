package com.android.a1000phone.chengling.national_day_homework.frist_page_fargment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.a1000phone.chengling.national_day_homework.GiftBean;
import com.android.a1000phone.chengling.national_day_homework.R;
import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;
import com.androidxx.yangjw.commonlibrary.ImageAsyncLoader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;


/**
 * Created by chengling on 2016/10/5.
 */
public class GiftBagFragment extends Fragment {
    private final String PATH = "http://www.1688wan.com/majax.action?method=getGiftList";
    private List<GiftBean> data = new ArrayList<>();
    private List<String> imagePaths = new ArrayList<>();
    private Context mContext;
    private MyAdapter myAdapter;
    private ListView mListView;
    private ViewPager mViewPager;
    private BannerAdapter bannerAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.libao_main_view,container,false);
        mListView = (ListView)view.findViewById(R.id.libao_list_view_lv);
        mViewPager = (ViewPager)view.findViewById(R.id.libao_heander_viewpager);
        AsyncTaskTool.load(PATH).post("pageno=1").execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                paeseJsonImage(result);
                parseJsonItem(result);
            }
        });
        myAdapter = new MyAdapter(mContext);
        mListView.setAdapter(myAdapter);
        bannerAdapter = new BannerAdapter();
        mViewPager.setAdapter(bannerAdapter);
        return view;
    }
    private void paeseJsonImage(String result){
        try {
            JSONObject jsonObjectimage = new JSONObject(result);
            JSONArray jsonArray = jsonObjectimage.getJSONArray("ad");
            int len  = jsonArray.length();
            for (int i = 0; i < len ; i++) {
                JSONObject adObject = jsonArray.getJSONObject(i);
                String id = adObject.getString("id");
                String iconurl = adObject.getString("iconurl");
                imagePaths.add(id);
                imagePaths.add(iconurl);

                Log.i("android__________-", "paeseJsonImage: +++++++++++"+iconurl);
            }
            bannerAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJsonItem(String result){
        try {
            JSONObject jsonObjectitem = new JSONObject(result);
            JSONArray jsonArray = jsonObjectitem.getJSONArray("list");
            int listlen = jsonArray.length();
            for (int i = 0; i < listlen; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                Long id = jsonObj.getLong("id");
                String iconurl = jsonObj.getString("iconurl");
                String gname = jsonObj.getString("gname");
                String giftname = jsonObj.getString("giftname");
                String number = jsonObj.getString("number");
                String addtime = jsonObj.getString("addtime");
                Log.i("android__________-", "paeseJsonImage: +++++++++++"+gname);
                GiftBean giftBean = new GiftBean(id,iconurl,gname,giftname,"剩余："+number,"时间："+addtime);
                data.add(giftBean);
            }
            myAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class MyAdapter extends BaseAdapter{
        private final String PREFIX = "http://www.1688wan.com";
        private LayoutInflater mLayoutInflater;

        public MyAdapter(Context context){
            super();
            this.mLayoutInflater = LayoutInflater.from(mContext);
        }
        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
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
                view = mLayoutInflater.inflate(R.layout.gift_main_view_item,parent,false);
                viewHolder = new ViewHolder(view);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }
            GiftBean bean = data.get(position);
            ImageAsyncLoader.load(PREFIX+bean.getPath(),viewHolder.mImageView).execute();
            viewHolder.mtitle.setText(bean.getgName());
            viewHolder.mSubtitle.setText(bean.getGiftName());
            viewHolder.mTime.setText(bean.getResidue()+bean.getTime());
            return view;
        }
    }
    class ViewHolder{
        public TextView mTime;
        private TextView mSubtitle;
        public ImageView mImageView;
        private TextView mtitle;
        public Button mButton;

        public ViewHolder(View view){
            view.setTag(this);
            mImageView = (ImageView)view.findViewById(R.id.gift_main_view_iv);
            mtitle = (TextView)view.findViewById(R.id.gift_main_view_title_txt);
            mSubtitle = (TextView)view.findViewById(R.id.gift_main_view_subtitle_txt);
            mTime = (TextView)view.findViewById(R.id.gift_main_view_time_txt);
            mButton = (Button)view.findViewById(R.id.gift_main_view_button_btn);


        }
    }

    class BannerAdapter extends PagerAdapter{

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
            String imgPath = "http://www.1688wan.com"+path;
            ImageAsyncLoader.load(imgPath,imageView).execute();
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
}
