package com.example.day30_didi_daren_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

public class MainActivity extends AppCompatActivity {

    private TextureMapView mapView;
    private BaiduMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView = (TextureMapView) findViewById(R.id.didi_daren_map_view);
        map = mapView.getMap();
        map.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                LatLng target = mapStatus.target;
                double latitude = target.latitude;
                double longitude = target.longitude;
                //利用经纬度获得地址信息
                GeoCoder geoCoder = GeoCoder.newInstance();
                ReverseGeoCodeOption option = new ReverseGeoCodeOption();
                option.location(target);
                geoCoder.reverseGeoCode(option);
                //设置监听，获得地址信息
                geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                    /**
                     * 地理编码信息
                     * @param geoCodeResult
                     */
                    @Override
                    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

                    }



                    /**
                     * 获取地址信息
                     * @param reverseGeoCodeResult
                     */
                    @Override
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                        String address = reverseGeoCodeResult.getAddress();
                        Toast.makeText(MainActivity.this,address,Toast.LENGTH_SHORT).show();
                    }
                });

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
