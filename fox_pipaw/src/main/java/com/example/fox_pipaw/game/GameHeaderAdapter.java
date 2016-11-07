package com.example.fox_pipaw.game;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fox_pipaw.Bean.GameHeaderBean;
import com.example.fox_pipaw.R;
import com.example.fox_pipaw.http.ImageAsyncLoader;

import java.util.List;

/**
 * Created by 张样 on 2016/10/25.
 */
public class GameHeaderAdapter extends PagerAdapter {

    private List<GameHeaderBean>datas;
    private Context mContext;

    public GameHeaderAdapter(List<GameHeaderBean> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        ImageView imageView = new ImageView(mContext);
//        Button button = new Button(mContext);
//        button.setText("请输入要搜索的内容");
//        container.addView(button);
//        container.addView(imageView);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        String imgPath = datas.get(position).getPath();
//        ImageAsyncLoader.load(imgPath,imageView).execute();
//        return imageView;

        View view1 = LayoutInflater.from(mContext).inflate(R.layout.game_header, null);
        String imgPath = datas.get(position).getPath();
        ImageView imageView = (ImageView) view1.findViewById(R.id.game_header_image);
        ImageAsyncLoader.load(imgPath,imageView).execute();
        container.addView(view1);

        return view1;

//        View view = LayoutInflater.from(mContext).inflate(R.layout.game_header, null);
//        container.addView(view);
//        String path = datas.get(position).getPath();
//        ImageAsyncLoader.load(path,
//                (ImageView) view.findViewById(R.id.game_header_image)).execute();
//        ViewHoudler viewHoudler = null;
//        View view;
//        if(container == null){
//            view = LayoutInflater.from(mContext).inflate(R.layout.game_header, null);
//            container.addView(view);
//            viewHoudler = new ViewHoudler(container);
//        }else{
//            viewHoudler = (ViewHoudler) container.getTag();
//        }
//        ImageAsyncLoader.load(datas.get(position).getPath(),viewHoudler.imageView);
//        return view;
    }
//    class ViewHoudler{
//        public ImageView imageView;
//        public Button mIearchBtn;
//        public Button mDownLoadBtn;
//
//        public ViewHoudler(View view) {
//            view.setTag(this);
//            imageView = (ImageView) view.findViewById(R.id.game_header_image);
//            mDownLoadBtn = (Button) view.findViewById(R.id.game_header_download_btn);
//            mIearchBtn = (Button) view.findViewById(R.id.game_header_search_btn);
//        }
//    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
