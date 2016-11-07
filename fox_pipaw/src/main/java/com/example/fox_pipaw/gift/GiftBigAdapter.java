package com.example.fox_pipaw.gift;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.fox_pipaw.Bean.GiftBigGiftBean;
import com.example.fox_pipaw.R;

import java.util.List;

/**
 * Created by 张样 on 2016/10/26.
 */
public class GiftBigAdapter extends BaseAdapter {
    private List<GiftBigGiftBean.DataBean> dataGigGift;
    private Context mContext;

    public GiftBigAdapter(List<GiftBigGiftBean.DataBean> dataGigGift, Context mContext) {
        this.dataGigGift = dataGigGift;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return dataGigGift == null ? 0 : dataGigGift.size();
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
        ViewHoudler viewHoudler = null;
        if(view == null){
            view =LayoutInflater.from(mContext).inflate(R.layout.gift_list_details,parent,false);
            viewHoudler = new ViewHoudler(view);
        }else{
            viewHoudler = (ViewHoudler) view.getTag();
        }
        GiftBigGiftBean.DataBean bean = dataGigGift.get(position);
        viewHoudler.mTitleTxt.setText(bean.getTitle());
        viewHoudler.mDescTxt.setText(bean.getDescription());
        viewHoudler.mRemainTxt.setText(bean.getRemain()+"%");
        viewHoudler.mPeopleNoTxt.setText(bean.getNumber()+"");
        return view;
    }
    class ViewHoudler{
        public TextView mTitleTxt;
        public TextView mDescTxt;
        public TextView mRemainTxt;
        public TextView mPeopleNoTxt;
        public Button btn;

        public ViewHoudler(View view) {
            view.setTag(this);
            mTitleTxt = (TextView) view.findViewById(R.id.gift_list_details_title_txt);
            mDescTxt = (TextView) view.findViewById(R.id.gift_list_details_desc_txt);
            mRemainTxt = (TextView) view.findViewById(R.id.gift_list_details_num_txt);
            mPeopleNoTxt = (TextView) view.findViewById(R.id.gift_list_details_people_txt);
            btn = (Button) view.findViewById(R.id.gift_list_details_btn);
        }
    }
}
