//package com.example.fox_pipaw.gift;
//
//import android.content.Context;
//import android.support.v4.view.PagerAdapter;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.example.fox_pipaw.Bean.GameHeaderBean;
//import com.example.fox_pipaw.Bean.GiftGuessBean;
//import com.example.fox_pipaw.Bean.GiftHeaderBean;
//import com.example.fox_pipaw.Log.LogValue;
//import com.example.fox_pipaw.R;
//import com.example.fox_pipaw.http.ImageAsyncLoader;
//
//import java.util.List;
//
///**
// * Created by 张样 on 2016/10/26.
// */
//public class GiftHeaderAdapter extends PagerAdapter {
////    private List<GiftHeaderBean.DataBean> datas;
//    private List<String>datas;
//    private Context mContext;
//
//    public GiftHeaderAdapter(List<String> datas, Context mContext) {
//        this.datas = datas;
//        this.mContext = mContext;
//    }
//
//    @Override
//    public int getCount() {
//        return datas == null ? 0 : 1;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.gift_header_image, null);
//        String imgPath1 = datas.get(position);
//        String imgPath2 = datas.get(position+1);
//        String imgPath3 = datas.get(position+2);
//        ImageView image1 = (ImageView) view.findViewById(R.id.gift_header_1_image);
//        ImageView image2 = (ImageView) view.findViewById(R.id.gift_header_2_image);
//        ImageView image3 = (ImageView) view.findViewById(R.id.gift_header_3_image);
//        ImageAsyncLoader.load(imgPath1,image1).execute();
//        ImageAsyncLoader.load(imgPath2,image2).execute();
//        ImageAsyncLoader.load(imgPath3,image3).execute();
//        return view;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
//    }
//}
