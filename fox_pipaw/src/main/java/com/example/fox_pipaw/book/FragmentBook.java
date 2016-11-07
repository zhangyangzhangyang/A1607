package com.example.fox_pipaw.book;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fox_pipaw.Bean.BookBean;
import com.example.fox_pipaw.R;
import com.example.fox_pipaw.http.AsyncTaskTool;
import com.example.fox_pipaw.http.HttpUrl;
import com.example.fox_pipaw.http.ImageAsyncLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/10/23.
 */
public class FragmentBook extends Fragment {
    private GridView mGridView;
    private List<BookBean> datas = new ArrayList<>();
    private GridViewAdapter gridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_main_book, container, false);
        mGridView = (GridView) view.findViewById(R.id.book_grid_view);
        initData();
        gridViewAdapter = new GridViewAdapter();
        mGridView.setAdapter(gridViewAdapter);
        return view;

    }

    private void initData() {
        AsyncTaskTool.load(HttpUrl.bookMainUrl).execute(new AsyncTaskTool.IMyCallback() {
            @Override
            public void success(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    int length = jsonArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String game_id = jsonObject.getString("game_id");
                        String game_name = jsonObject.getString("game_name");
                        String img = jsonObject.getString("img");
                        BookBean bookBean = new BookBean(game_id,img,game_name);
                        datas.add(bookBean);
                    }
                    gridViewAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class GridViewAdapter extends BaseAdapter{
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
            ViewHouder viewHouder = null;
            if (view == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.book_gridview_image,parent,false);
                 viewHouder = new ViewHouder(view);
            }else{
                viewHouder = (ViewHouder) view.getTag();
            }
            ImageAsyncLoader.load(datas.get(position).getImgPath(),viewHouder.imageView).execute();
            viewHouder.mGameName.setText(datas.get(position).getName());
            return view;
        }
        class ViewHouder{
            public ImageView imageView;
            public TextView mGameName;
            public ViewHouder(View view){
                view.setTag(this);
                imageView = (ImageView) view.findViewById(R.id.book_grid_image);
                mGameName = (TextView) view.findViewById(R.id.book_grid_text);
            }
        }
    }
}
