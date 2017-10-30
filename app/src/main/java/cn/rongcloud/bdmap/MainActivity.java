package cn.rongcloud.bdmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private MapView mMapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        BaiduMap mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //定义Maker坐标点
         List<Map<String,Object>> maps=new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new HashMap<String, Object>();
        map1.put("lat",39.963175f);
        map1.put("lng",116.400244f);
        map1.put("url","");
        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("lat",45.963175f);
        map2.put("lng",100.400244f);
        map2.put("url","");
        maps.add(map1);
        maps.add(map2);

        List<OverlayOptions> overlayOptions=new ArrayList<OverlayOptions>();
        for(int x=0;x<maps.size();x++){
            Map<String,Object> map=maps.get(x);
            LatLng point = new LatLng((float)(map.get("lat")), (float)(map.get("lng")));
            //构建Marker图标
            //BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
            View view=LayoutInflater.from(MainActivity.this).inflate(R.layout.bitmapdesriptor,null);
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);
            //构建MarkerOption，用于在地图上添加Marker
           // OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
            OverlayOptions option=new MarkerOptions().position(point).flat(false).title("aaaa"+x).icon(bitmap);
            overlayOptions.add(option);
        }
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlays(overlayOptions);


        //图Logo
        //mMapView.setLogoPosition(LogoPosition.logoPostionleftBottom);


//普通地图 ,mBaiduMap是地图控制器对象

//        // 开启定位图层
//        mBaiduMap.setMyLocationEnabled(true);
//
//        //MyLocationConfiguration.LocationMode mCurrentMode = LocationMode.FOLLOWING;//定位跟随态
//        //MyLocationConfiguration.LocationMode mCurrentMode = LocationMode.NORMAL;   //默认为 LocationMode.NORMAL 普通态
//        //MyLocationConfiguration.LocationMode mCurrentMode = LocationMode.COMPASS;  //定位罗盘态
//// 构造定位数据
//        BDLocation location=new BDLocation();
//        MyLocationData locData = new MyLocationData.Builder()
//                .accuracy(location.getRadius())
//                // 此处设置开发者获取到的方向信息，顺时针0-360
//                .direction(100).latitude(location.getLatitude())
//                .longitude(location.getLongitude()).build();
//
//// 设置定位数据
//        mBaiduMap.setMyLocationData(locData);
//
//// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
//        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
//                .fromResource(R.mipmap.ic_launcher);
//        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
//        mBaiduMap.setMyLocationConfiguration(config);
//
//// 当不需要定位图层时关闭定位图层
//        mBaiduMap.setMyLocationEnabled(false);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
