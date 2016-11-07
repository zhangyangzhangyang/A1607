package com.example.fox_pipaw.gift;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fox_pipaw.Bean.GiftGuessBean;
import com.example.fox_pipaw.R;
import com.example.fox_pipaw.http.ImageAsyncLoader;

import java.util.List;

/**
 * Created by 张样 on 2016/10/26.
 */
public class GiftGuessAdapter extends BaseAdapter {
    private List<GiftGuessBean.DataBean>datasGuess;
    private Context mContext;

    public GiftGuessAdapter(List<GiftGuessBean.DataBean> datasGuess, Context mContext) {
        this.datasGuess = datasGuess;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return datasGuess == null ? 0 : datasGuess.size();
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
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.gift_guess_list_details, parent, false);
            viewHoudler =new ViewHoudler(view);
        }else{
            viewHoudler = (ViewHoudler) view.getTag();
        }
        GiftGuessBean.DataBean dataBean = datasGuess.get(position);
        ImageAsyncLoader.load(dataBean.getGame_logo(),viewHoudler.imageView).execute();
        viewHoudler.mTitleTxt.setText(dataBean.getGame_name());
        viewHoudler.mGiftBagNumTxt.setText("礼包："+dataBean.getNum()+"种");
        viewHoudler.mPopleNumTxt.setText(dataBean.getGame_visits());
        return view;
    }
    class ViewHoudler{
        public ImageView imageView;
        public TextView mTitleTxt;
        public TextView mGiftBagNumTxt;
        public TextView mPopleNumTxt;
        public Button mBtn;

        public ViewHoudler(View view) {
            view.setTag(this);
            imageView = (ImageView) view.findViewById(R.id.gift_guess_list_details_image);
            mTitleTxt = (TextView) view.findViewById(R.id.gift_guess_list_details_title_txt);
            mGiftBagNumTxt = (TextView) view.findViewById(R.id.gift_guess_list_details_giftbag_txt);
            mPopleNumTxt = (TextView) view.findViewById(R.id.gift_guess_list_details_peopleno_txt);
            mBtn = (Button) view.findViewById(R.id.gift_guess_list_details_btn);
        }
    }
}
