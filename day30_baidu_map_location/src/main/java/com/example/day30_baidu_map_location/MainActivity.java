package com.example.day30_baidu_map_location;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.TextureMapView;

public class MainActivity extends AppCompatActivity {

    private TextureMapView mapView;
    private BaiduMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView = (TextureMapView) findViewById(R.id.didi_map_view);
        map = mapView.getMap();
        //当地图拖动的时候，会触发此监听，并且会自动返回拖动后地图中心点的坐标
        map.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            /**
             * 改变开始
             * @param mapStatus
             */
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            /**
             * 正在发生变化
             * @param mapStatus
             */
            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            /**
             * 拖动结束
             * @param mapStatus 改变之后的地理信息
             */
            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
